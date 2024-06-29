<template>
  <div class="container">
    <h1 class="text-center mt-4">員工座位安排系統</h1>
    <form class="row mt-4 mb-4 form-border align-items-center">
      <div class="col-3">
          <label for="employeeId" class="form-label"><h5>員工編號</h5></label>
          <select class="form-select" v-model="selectedEmp" id="employeeId">
            <option v-for="employee in employees" :key="employee.empId" :value="employee">
              {{ employee.empId }}
            </option>
          </select>
      </div>  
      <div class="col-3">
          <label for="employeeName" class="form-label mb-0"><h5>員工名稱</h5></label>
          <div class="form-control-plaintext text-border" id="employeeName">
            {{selectedEmp ? selectedEmp.name : '' }}
          </div>
      </div>
      <div class="col-3">
          <label for="employeeEmail" class="form-label mb-0"><h5>員工信箱</h5></label>
          <div class="form-control-plaintext text-border" id="employeeEmail">
            {{selectedEmp ? selectedEmp.email : '' }}
          </div>
      </div>
    </form>
    
    <div class="row row-cols-4">
      <SeatInfo v-for="seat in seats" :key="seat.floorSeatSeq"
        :floorNo="seat.floorNo" :seatNo="seat.seatNo" :employeeId="seat.empId"
        :isSelected="seat.isSelected"
        @click.native="handleSeatClick(seat)" />
    </div>
    <div class="row">
      <div class="col-6">
        <div class="legend">
          <div class="legend-item">
            <div class="color-box empty"></div>
            <span>空位</span>
          </div>
          <div class="legend-item">
            <div class="color-box occupied"></div>
            <span>已佔用</span>
          </div>
          <div class="legend-item">
            <div class="color-box selected"></div>
            <span>請選擇</span>
          </div>
        </div>
      </div>
      <div class="col-6 d-flex align-items-start justify-content-end">
        <button class="me-2 btn btn-secondary" @click="removeSeat">清除</button>
        <button class="btn btn-primary " @click="updateSeat">送出</button>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import SeatInfo from '../component/SeatInfo.vue';

  export default {
    name: 'Home',
    components: {
      SeatInfo
    },
    data() {
      return {
        employees: [],
        selectedEmp: null,
        floorSeatSeq:null,
        seats: [],
      };
    },
    mounted() {
      // 取得員工資料
      axios.get('http://localhost:8080/api/allemployee')
        .then(response => {
          this.employees = response.data;
          // 預設第一個員工顯示
          this.selectedEmp = this.employees[0];
        })
        .catch(error => {
          console.error(error);
        });
      // 取得座位資料 
      this.fetchSeats();
    },
    methods: {
      // 取得座位資料，給mounted跟其他method使用
      fetchSeats() {
        axios.get('http://localhost:8080/api/allseat')
          .then(response => {
            this.seats = response.data.map(seat => ({ ...seat, isSelected: false }));
          })
          .catch(error => {
            console.error(error);
          });
      },
      handleSeatClick(seat) {
        this.seats.forEach(s => s.isSelected = false);
        this.seats.forEach(s => s.employeeId = null);
        seat.isSelected = true;
        seat.employeeId = this.selectedEmp.empId;
        this.floorSeatSeq = seat.floorSeatSeq;
      },
      updateSeat() {
        let empId = this.selectedEmp.empId;
        // 送出表單前，先確認座位是否已被佔用
        axios.get('http://localhost:8080/api/checkseat',{
            params: {floorSeatSeq: this.floorSeatSeq}
          }).then(response => {
            return response.data 
          }).then(isEmpty => {
            // 空的位置，送出表單
            if(isEmpty){
              axios.put('http://localhost:8080/api/updateseat', 
                new URLSearchParams({
                  empId: empId,
                  floorSeatSeq: this.floorSeatSeq
                })).then(response => {
                  console.log(response.data);
                  this.fetchSeats()
                })
                .catch(error => {
                  console.error(error);
                });
            }
            // 非空的位置，請重新選擇
            if(!isEmpty){
              alert("座位已被佔用，請重新選擇");
              this.fetchSeats();
            }
          })
          .catch(error => {
            console.error(error);
          });
      },
      removeSeat(){
        let empId = this.selectedEmp.empId;
        axios.put('http://localhost:8080/api/removeseat', 
        new URLSearchParams({
          empId: empId,
        })).then(response => {
          console.log(response.data);
          this.fetchSeats()
        })
        .catch(error => {
          console.error(error);
        });
      }
    }
  };
</script>

<style scoped>
  .form-border {
    border: 1px solid #757575;
    padding: 1em;
    border-radius: 1em;
  }

  .selected-seat {
    background-color: lightgreen;
  }

  .text-border {
    border: 1px solid #757575;
    padding-left: 1em;
  }

  .legend {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .legend-item {
    display: flex;
    align-items: center;
    gap: 10px;
  }

  .color-box {
    width:  40px;
    height: 30px;
    border-radius: 5px;
  }

  .empty {
    background-color: #f5f5f5;
    border: 1px solid #8f8f8f; 
  }

  .occupied {
    background-color: pink;
  }
  .selected {
    background-color: rgb(133, 216, 133);
  }

</style>
