<script setup lang="ts">

import {computed} from "vue";

const prop = defineProps({
  item:{
    type:Object,
    required:true,
  }
})

const menutype2chinese = computed(()=>{
  switch (prop.item.menuType){
    case 'DIRECTORY' :return '目录'
    case 'MENU' :return '菜单'
    case 'BUTTON' :return '按钮'
  }

})

</script>

<template>
<div>
  <input type="checkbox" :checked="prop.item.checked">
  <span>{{item.name}}</span>
  <span class="menuType">{{menutype2chinese}}</span>
  <div v-if="item.children && item.children.length>0" class="subitem">
    <Mymenu v-for="subitem in item.children " :item="subitem"></Mymenu>
  </div>
</div>
</template>

<style scoped>
.subitem{
  padding-left: 20px;
}
.menuType{
  float: right;
}
</style>