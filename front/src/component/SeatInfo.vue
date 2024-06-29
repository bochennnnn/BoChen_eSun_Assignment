<template>
  <div class="col mb-4">
    <div :class="['card', {'selected-seat': isSelected },{ 'ordered-seat': employeeId }]" 
    :style="{ pointerEvents: employeeId ? 'none' : 'auto' }" 
    @click="emitClick">
      <div class="card-body">
        <h4 class="seat-content">{{ floorNo }}樓: 座位{{ seatNo }}</h4>
        <h5 v-if="employeeId" class="mt-2">[員編: {{ employeeId }}]</h5>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'SeatInfo',
    props: {
      floorNo: Number,
      seatNo: Number,
      employeeId: [String, Number],
      isSelected: Boolean
    },
    methods: {
      emitClick() {
        this.$emit('click');
      }
    }
  };
</script>

<style scoped>
  .card{
    width: 100%;
    height: 100px;
    border-radius: 1em;
    background-color: #f5f5f5;
    border: #cccccc 1.5px solid;
    transition: background-color 0.3s; 
  }
  .card-body {
    display: flex;
    flex-grow: 1;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
  }
  .selected-seat {
    background-color: rgb(133, 216, 133);
  }
  .ordered-seat {
    background-color:pink;
  }
  .seat-content {
    margin: 0;
  }
  .card:hover {
    background-color: #cccccc;
  }
</style>
