<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" v-if="hasPerm('permission:add')" @click="showCreate">添加权限
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="权限" prop="permissionName" width="170"></el-table-column>
      <el-table-column align="center" label="角色" prop="menuName" style="width: 60px;">
        <template slot-scope="scope">
          <div v-for="role in scope.row.roles">
            <div v-text="role.roleName" style="display: inline-block;vertical-align: middle;"></div>
          </div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="权限编码" prop="permissionCode"> </el-table-column>
      <el-table-column align="center" label="归属菜单" prop="menuName" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="归属菜单编码" prop="menuCode"></el-table-column>
      <el-table-column align="center" label="是否必选" width="170">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.requiredPermission == 'T'" v-text="'是'"></el-tag>
          <el-tag type="primary" v-else v-text="'否'"></el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="220" v-if="hasPerm('permission:update') | hasPerm('permission:delete')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" v-if="hasPerm('permission:update')" @click="showUpdate(scope.$index)">修改</el-button>
          <el-button type="danger" icon="delete" v-if="(!scope.row.roles | scope.row.roles.length === 0) && hasPerm('permission:delete')"
                     @click="removePermission(scope.$index)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempPermission" label-position="left" label-width="120px"
               style='width: 360px; margin-left:50px;'>
        <el-form-item label="权限" required>
          <el-input type="text" v-model="tempPermission.permissionName">
          </el-input>
        </el-form-item>
        <el-form-item label="权限编码" required>
          <el-input type="text" v-model="tempPermission.permissionCode">
          </el-input>
        </el-form-item>
        <el-form-item label="归属菜单" required>
          <el-input type="text" v-model="tempPermission.menuName">
          </el-input>
        </el-form-item>
        <el-form-item label="归属菜单编码" required>
          <el-input type="text" v-model="tempPermission.menuCode">
          </el-input>
        </el-form-item>
        <el-form-item label="是否必选" required>
          <el-select v-model="tempPermission.requiredPermission" placeholder="请选择">
            <el-option
              :key="notRequired"
              label="否"
              :value="notRequired">
            </el-option>
            <el-option
              :key="required"
              label="是"
              :value="required">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createPermission">创 建</el-button>
        <el-button type="primary" v-else @click="updatePermission">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'

  export default {
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 10,//每页条数
        },
        roles: [],//角色列表
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '新建权限'
        },
        required: 'T',
        notRequired: 'F',
        tempPermission: {
          permissionId: '',
          menuCode: '',
          menuName: '',
          permissionCode: '',
          permissionName: '',
          requiredPermission: ''
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        this.listLoading = true;
        this.api({
          url: "/user/listPermission",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getList();
      },
      handleFilter() {
        //查询事件
        this.listQuery.pageNum = 1
        this.getList()
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.tempPermission.menuCode = "";
        this.tempPermission.menuName = "";
        this.tempPermission.permissionCode = "";
        this.tempPermission.permissionName = "";
        this.tempPermission.requiredPermission = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        let permission = this.list[$index];
        this.tempPermission.permissionId = permission.permissionId;
        this.tempPermission.menuCode = permission.menuCode;
        this.tempPermission.menuName = permission.menuName;
        this.tempPermission.permissionCode = permission.permissionCode;
        this.tempPermission.permissionName = permission.permissionName;
        this.tempPermission.deleteStatus = '1';
        this.tempPermission.requiredPermission = permission.requiredPermission;
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createPermission() {
        //添加新权限
        this.api({
          url: "/user/addPermission",
          method: "post",
          data: this.tempPermission
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updatePermission() {
        //修改权限信息
        let _vue = this;
        this.api({
          url: "/user/updatePermission",
          method: "post",
          data: this.tempPermission
        }).then(() => {
          let msg = "修改成功";
          this.dialogFormVisible = false
          this.$message({
            message: msg,
            type: 'success',
            duration: 1 * 1000,
            onClose: () => {
              _vue.getList();
            }
          })
        })
      },
      removePermission($index) {
        let _vue = this;
        this.$confirm('确定删除此权限?', '提示', {
          confirmButtonText: '确定',
          showCancelButton: false,
          type: 'warning'
        }).then(() => {
          let permission = _vue.list[$index];
          _vue.api({
            url: "/user/deletePermission",
            method: "post",
            data: permission
          }).then(() => {
            _vue.getList()
          }).catch(() => {
            _vue.$message.error("删除失败")
          })
        })
      },
    }
  }
</script>
