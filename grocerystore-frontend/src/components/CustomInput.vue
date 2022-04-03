<template>
  <div>
    <div>
      <label>
        {{ label }}
      </label>
    </div>
    <div>
      <input
        :type="type"
        :pattern="pattern"
        v-model="inputValue"
        :placeholder="placeholder"
        :step="step"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "CustomInput",
  props: ["label", "value", "type", "pattern", "placeholder","step"],
  computed: {
    inputValue: {
      get() {
        return this.value;
      },
      set(v) {
        this.$emit("change", v);
      }
    }
  }
};
</script>

<style scoped></style>
