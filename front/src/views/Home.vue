<template>
  <div class="container">
    <div class="input-group mt-5 mb-5">
      <select class="form-select" v-model="selectedEmp">
        <option v-for="employee in employees" :key="employee.empId" :value="employee">
          {{ employee.empId }}
        </option>
      </select>
      
      <input type="text" class="form-control" :value="selectedEmp ? selectedEmp.name : ''" readonly />
    </div>
    <div class="seat-container justify-content-center">
      <SeatInfo v-for="seat in seats" :key="seat.floorSeatSeq"
        :floorNo="seat.floorNo" :seatNo="seat.seatNo" :employeeId="seat.empId"
        :isSelected="seat.isSelected"
        @click.native="handleSeatClick(seat)" />
    </div>

    <button class="mt-3 btn btn-primary " @click="updateSeat">送出</button>

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
        this.selectedEmp = this.employees[0];
        
      })
      .catch(error => {
        console.error(error);
      });

    axios.get('http://localhost:8080/api/allseat')
      .then(response => {
        this.seats = response.data.map(seat => ({ ...seat, isSelected: false }));
        console.log(this.seats )
      })
      .catch(error => {
        console.error(error);
      });
  },
  methods: {
    handleSeatClick(seat) {
      this.seats.forEach(s => s.isSelected = false)
      this.seats.forEach(s => s.employeeId = null)
      seat.isSelected = true;
      seat.employeeId = this.selectedEmp.empId;
      this.floorSeatSeq = seat.floorSeatSeq;
    },
    updateSeat() {
      let empId = this.selectedEmp.empId;
      axios.post('http://localhost:8080/api/updateseat', 
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
    }
  }
};
</script>

<style scoped>
.seat-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
.selected-seat {
  background-color: lightgreen;
}
</style>
