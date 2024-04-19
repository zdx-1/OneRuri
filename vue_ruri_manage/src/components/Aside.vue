<template>
  <div class="aside" :class="{ 'aside-open': isSidebarOpen }" style="width: 200px">
    <div class="logo" style="width: 60px;height: 60px;display: flex;justify-content: center;align-items: center">
      <img src="../assets/logo.png" alt="logo" style="width: 60px;height: 60px;display: flex;justify-content: center;align-items: center"/>
    </div>
    <el-container>
      <el-menu :default-active="$route.path" router class="el-menu-vertical-demo">
        <template v-for="(item, index) in menuList">
          <el-submenu v-if="item.children && item.children.length > 0" :index="item.path">
            <template slot="title">
              <i :class="item.icon"></i>
              <span>{{ item.name }}</span>
            </template>
            <el-menu-item-group v-for="(childGroup, childIndex) in item.children" :key="childIndex">
              <template slot="title">
                {{ childGroup.name || 'Group ' + childIndex }}
              </template>
              <el-menu-item
                  v-for="(childItem, childItemIndex) in childGroup.items"
                  :key="childItemIndex"
                  :index="childItem.path"
              >
                <i :class="childItem.icon"></i>
                <span>{{ childItem.name }}</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item v-else :index="item.path">
            <i :class="item.icon"></i>
            <span>{{ item.name }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-container>

  </div>
</template>
<script>

export default {
  name: 'Aside',
  computed: {
  },
  data() {
    return {
      isSidebarOpen: false, // 控制侧边栏显示与隐藏的数据属性  ,
      menuList: []
    }
  },
  methods: {

  },
  mounted() {
    let AdminInfo = JSON.parse(sessionStorage.getItem('AdminInfo'))
    console.log(AdminInfo)
    if (AdminInfo.adminPower === 1) {
      this.menuList = [
        {
          name: '首页',
          path: '/home',
          icon: 'el-icon-s-home'
        },
        {
          name: '管理员管理',
          path: '/adminManage',
          icon: 'el-icon-user-solid',
          children: [
            {
              name: '管理员列表',
              path: '/adminManage/adminList',
            },
            {
              name: '添加管理员',
              path: '/adminManage/addAdmin',
            }
          ]
        },
        {
          name: '用户管理',
          path: '/admin_manage',
          icon: 'el-icon-user-solid'
        }]
    }
    if (AdminInfo.adminPower === 0) {
      this.menuList = [
        {
          name: '首页',
          path: '/home',
          icon: 'el-icon-s-home'
        },
        {
          name: '用户管理',
          path: '/userManage',
          icon: 'el-icon-user-solid'
        }]
    }
  }
}
</script>
<style scoped>/* 侧边栏容器样式 */
.aside {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  background-color: #304156; /* 侧边栏背景色 */
  color: #fff; /* 文本颜色 */
  overflow-y: auto; /* 垂直滚动 */
  z-index: 1000; /* 确保侧边栏在其他内容之上 */
  transition: transform 0.3s ease-in-out; /* 平滑过渡效果 */
}

/* Logo 样式 */
.logo img {
  width: 100%;
  height: auto;
  display: block; /* 移除内联元素的额外空间 */
}

/* 菜单容器样式 */
.el-menu-vertical-demo {
  width: 100%; /* 侧边栏宽度 */
  background-color: transparent; /* 移除Element UI默认背景色 */
}

/* 菜单项样式 */
.el-menu-item,
.el-submenu__title {
  font-size: 14px; /* 字体大小 */
  padding: 12px 20px; /* 内边距 */
  cursor: pointer; /* 鼠标悬停时变为手型 */
  color: inherit; /* 继承父级文本颜色 */
}

/* 菜单项悬停样式 */
.el-menu-item:hover,
.el-submenu__title:hover {
  background-color: rgba(255, 255, 255, 0.1); /* 悬停背景色 */
}

/* 激活菜单项样式 */
.el-menu-item.is-active,
.el-submenu__title.is-active {
  background-color: rgba(255, 255, 255, 0.2); /* 激活背景色 */
  font-weight: bold; /* 加粗字体 */
}

/* 子菜单样式 */
.el-submenu .el-menu-item {
  padding-left: 30px; /* 增加左侧内边距以区分层级 */
}

/* 图标样式 */
.el-menu i {
  margin-right: 8px; /* 图标与文本之间的间距 */
  font-size: 16px; /* 图标大小 */
  vertical-align: middle; /* 垂直居中 */
}

/* 响应式样式，当屏幕较小时隐藏侧边栏 */
@media (max-width: 768px) {
  .aside {
    transform: translateX(-200px); /* 将侧边栏移出视口 */
  }
  /* 添加类名来控制侧边栏的显示与隐藏，例如使用 `.aside-open` 来显示侧边栏 */
  .aside-open .aside {
    transform: translateX(0); /* 将侧边栏移回视口 */
  }
}


/* 收起/展开按钮样式 */
.aside-footer {
  width: 100%;
  text-align: center; /* 按钮居中显示 */
  background-color: #263238; /* 与侧边栏背景色相似，但稍深一些以区分 */
}

/* 侧边栏过渡效果 */
.aside {
  transition: transform 0.3s ease-in-out; /* 平滑过渡效果 */
}

</style>
