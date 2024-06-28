<template>
  <div :class="['border', 'p-4', 'text-center', 'seatinfo', { 'selected-seat': isSelected }, 
  { 'ordered-seat': employeeId }]" 
  :style="{ pointerEvents: employeeId ? 'none' : 'auto' }" @click="emitClick">
    <h4 class="seat-content">{{ floorNo }}樓: 座位{{ seatNo }}</h4>
    <h5 v-if="employeeId" class="mt-3">[員編: {{ employeeId }}]</h5>
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
  .seatinfo {
    width: 19%;
    height: 100px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 1em;
    background-color: #f5f5f5;
    transition: background-color 0.3s; /* Smooth transition for hover effect */
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
  .seatinfo:hover {
    background-color: #cccccc; /* Change color on hover */
  }
</style>
