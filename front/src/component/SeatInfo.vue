<template>
  <div :class="['border','p-4','text-center','seatinfo', { 'selected-seat': isSelected }, 
  { 'ordered-seat': employeeId }]" :style="{ pointerEvents: employeeId ? 'none' : 'auto' }" @click="emitClick">
    <h5>{{ floorNo }}樓: 座位{{ seatNo }}
    <p v-if="employeeId" class="mt-3">員編: {{ employeeId }}</p>
    </h5>
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
.seatinfo{
  width: 20%;
  border-radius: 1em;
  background-color: #f5f5f5;
}
.selected-seat {
  background-color: lightgreen;
}
.ordered-seat{
  background-color: red;
}
</style>
