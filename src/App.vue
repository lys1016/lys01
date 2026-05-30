<script setup lang="ts">

import {onMounted, ref} from "vue";
import {getAllPermission, getPermissionOfrole, getRoleList, type Role} from "@/http/role.ts";
import Mymenu from "@/Mymenu.vue";

interface Permission{
  id:number,
  name:string,
  checked:boolean,
  children:[],
}

const isHide = ref(true);

const roleList = ref<Role[]>([]);



const allPermission = ref<Permission>([])

onMounted(async () => {
  roleList.value = await getRoleList()
  console.log("角色数据是：{}",roleList.value)
})

// 打开遮罩层
const openAuthDialog = async (roleId:number) => {
  //拿到角色已有的权限id
  const rolepermissionIds = await getPermissionOfrole(roleId);
  console.log("这个角色有的权限：",rolepermissionIds);

  allPermission.value = await getAllPermission()
  console.log("权限有：{}",allPermission.value);
  isHide.value = false;
  //添加check属性
  addChecked(allPermission.value)
  //实现勾选已有权限
  checkPermission(allPermission.value,rolepermissionIds)
}

const   checkPermission=(allPermission:Permission[],rolepermissionIds:number[])=>{
     allPermission.forEach(item=>{
       if (rolepermissionIds.includes(item.id)){
         item.checked = true;
       }
       if (item.children && item.children.length>0){
         checkPermission(item.children,rolepermissionIds)
       }

     })
}

const addChecked = (treeData:Permission[]) =>{
  treeData.forEach(p=>{
    p.checked = false;
    if(p.children && p.children.length>0){
      addChecked(p.children)
    }
  })
}

const closeAuthDialog = () => {
  isHide.value = true;
}
</script>

<template>
  <table>
    <tr>
      <th>角色ID</th>
      <th>角色名称</th>
      <th>操作</th>
    </tr>
    <tr v-for="role in roleList" :key="role.id">
      <td>{{ role.id }}</td>
      <td>{{ role.name }}</td>
      <td>
        <button @click="openAuthDialog(role.id)">授权</button>
      </td>
    </tr>
  </table>
  <!-- 遮罩层（初始隐藏） -->
  <div id="rbacModal" class="rbac-modal-overlay" :class="{modelHide: isHide}" @click.self="closeAuthDialog">
    <div class="rbac-modal-content" id="modalContent">
      <span class="rbac-modal-close" id="closeModal" @click="closeAuthDialog">&times;</span>
      <div style="padding: 20px; height: 100%; box-sizing: border-box;">
        <h2>角色授权</h2>
        <Mymenu v-for="menu in allPermission"
                :key="menu.id"
        :item="menu"></Mymenu>
      </div>
    </div>
  </div>
</template>

<style scoped>
table, tr, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}

/* 遮罩层容器 */
.rbac-modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5); /* 半透明黑色 */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

/* 遮罩内容区 */
.rbac-modal-content {
  width: 640px;
  height: 560px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  position: relative;
  overflow: hidden;
}

/* 关闭按钮示例（可选） */
.rbac-modal-close {
  position: absolute;
  top: 16px;
  right: 16px;
  cursor: pointer;
  font-size: 20px;
  color: #999;
}

.modelHide {
  display: none;
}

</style>
