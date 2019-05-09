<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('device:add')">添加设备
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
      <el-table-column align="center" prop="sn" label="设备" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="添加时间" width="170">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="最近修改时间" width="170">
        <template slot-scope="scope">
          <span>{{scope.row.updateTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('device:update') || hasPerm('device:delete')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" v-if="hasPerm('device:update')" @click="showUpdate(scope.$index)">修改</el-button>
          <el-button type="danger" icon="delete" v-if="hasPerm('device:delete')" @click="removeDevice(scope.$index)">删除</el-button>
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
      <el-form class="small-space" :model="tempDevice" label-position="left" label-width="60px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="设备">
          <el-input type="text" v-model="tempDevice.sn">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createDevice">添加</el-button>
        <el-button type="primary" v-else @click="updateDevice">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 10,//每页条数
          name: ''
        },
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '添加设备'
        },
        tempDevice: {
          id: "",
          sn: ""
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('device:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/device/listDevice",
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
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.tempDevice.sn = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        //显示修改对话框
        this.tempDevice.id = this.list[$index].id;
        this.tempDevice.sn = this.list[$index].sn;
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createDevice() {
        //保存新设备
        this.api({
          url: "/device/addDevice",
          method: "post",
          data: this.tempDevice
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateDevice() {
        //修改设备
        this.api({
          url: "/device/updateDevice",
          method: "post",
          data: this.tempDevice
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      removeDevice($index) {
        let _vue = this;
        this.$confirm('确定删除此设备?', '提示', {
          confirmButtonText: '确定',
          showCancelButton: false,
          type: 'warning'
        }).then(() => {
          let device = _vue.list[$index];
          _vue.api({
            url: "/device/deleteDevice",
            method: "post",
            data: {
              id: device.id
            }
          }).then(() => {
            _vue.getList()
          }).catch(e => {
          })
        })
      }
    }
  }
</script>
