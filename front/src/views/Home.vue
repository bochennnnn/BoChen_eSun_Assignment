<template>
  <div class="container">
    <div class="row mt-4 mb-4 align-items-center">
      <div class="col-3 align-items-center justify-content-end">
        <label for="employeeId" class="form-label mb-0"><h5>員工編號</h5></label>
      </div>
      <div class="col-3">
        <select id="employeeId" class="form-select" v-model="selectedEmp">
          <option v-for="employee in employees" :key="employee.empId" :value="employee">
            {{ employee.empId }}
          </option>
        </select>
      </div>
      <div class="col-3 d-flex align-items-center justify-content-end">
        <label for="employeeName" class="form-label mb-0"><h5>員工姓名</h5></label>
      </div>
      <div class="col-3">
        <input id="employeeName" type="text" class="form-control" :value="selectedEmp ? selectedEmp.name : ''" readonly />
      </div>     
    </div>

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
            <div class="color-box selected"></div>
            <span>請選擇</span>
          </div>
          <div class="legend-item">
            <div class="color-box occupied"></div>
            <span>已佔用</span>
          </div>
          <div class="legend-item">
            <div class="color-box empty"></div>
            <span>空位</span>
          </div>
        </div>
      </div>
      <div class="col-6 d-flex align-items-start justify-content-end">
        <button class="me-3 btn btn-secondary" @click="removeSeat">清除</button>
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
        seats: []
      };
    },
    mounted() {
      axios.get('http://localhost:8080/api/allemployee')
        .then(response => {
          this.employees = response.data;
          // 預設第一個員工顯示
          this.selectedEmp = this.employees[0];
          
        })
        .catch(error => {
          console.error(error);
        });

      axios.get('http://localhost:8080/api/allseat')
        .then(response => {
          this.seats = response.data.map(seat => ({ ...seat, isSelected: false }));
        })
        .catch(error => {
          console.error(error);
        });
    },
    methods: {
      handleSeatClick(seat) {
        this.seats.forEach(s => s.isSelected = false);
        this.seats.forEach(s => s.employeeId = null);
        seat.isSelected = true;
        seat.employeeId = this.selectedEmp.empId;
        this.floorSeatSeq = seat.floorSeatSeq;
      },
      updateSeat() {
        let empId = this.selectedEmp.empId;
        axios.put('http://localhost:8080/api/updateseat', 
        new URLSearchParams({
          empId: empId,
          floorSeatSeq: this.floorSeatSeq
        })).then(response => {
          console.log(response.data);
          window.location.reload();
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
          window.location.reload();
        })
        .catch(error => {
          console.error(error);
        });
      }
    }
  };
</script>

<style scoped>
  .selected-seat {
    background-color: lightgreen;
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
    border: 1px solid #ccc; /* Optional: Add a border for better visibility */
  }

  .occupied {
    background-color: pink;
  }
  .selected {
    background-color: rgb(133, 216, 133);
  }
</style>
