<script setup lang="ts">
import {computed} from "vue";

const prop = defineProps({
  item: {
    type:Object,
    required: true
  }
})

const typeName2chinese = computed(()=>{
  switch (prop.item.menuType){
    case 'DIRECTORY' : return '目录'
    case 'MENU': return '菜单'
    case 'BUTTON':return'按钮'
  }
})




</script>

<template>
  <div>
    <input type="checkbox" :checked = item.checked>
    <span>{{item.name}}</span>
    <span class="menuType">{{typeName2chinese}}</span>
    <div class="children" v-if="item.children && item.children.length>0">
      <Mymenu v-for="subitem in item.children"
              :key="subitem.id"
              :item="subitem"></Mymenu>
    </div>
  </div>

</template>

<style scoped>
  .children{
    padding-left: 20px;
  }
  .menuType{
    float: right;
  }
</style>