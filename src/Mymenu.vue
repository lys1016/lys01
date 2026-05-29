<script setup lang="ts">
defineProps({
  item: {
    type:Object,
    required: true
  }
})


</script>

<template>
  <div>
    <input type="checkbox" :checked = false>
    <span>{{item.name}}</span>
    <div class="children" v-if="item.children && item.children.length>0"
    v-for="subitem in item.children"
         :key="subitem.id"

    >
      <Mymenu :item="subitem"></Mymenu>
    </div>
  </div>

</template>

<style scoped>
  .children{
    padding-left: 20px;
  }
</style>