<template>
  <div :class="classObj" class="app-wrapper">
    <div v-if="device==='mobiyle'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside" />
    <sidebar class="sidebar-container" />
    <div :class="{'hasTagsView':needTagsView}" class="main-container">
      <div id="fixed-header" :style="{width: width}" :class="{'fixed-header':true}">
        <navbar />
        <tags-view />
      </div>
      <app-main />
    </div>
  </div>
</template>

<script>
import { Navbar, Sidebar, AppMain, TagsView, Settings } from "./components";
import ResizeMixin from "./mixin/ResizeHandler";

export default {
  name: "Layout",
  components: {
    Navbar,
    Sidebar,
    AppMain,
    TagsView,
    Settings
  },
  data(){
    return {
      width: null
    }
  },
  mixins: [ResizeMixin],
  computed: {
    sidebar() {
      return this.$store.state.app.sidebar;
    },
    device() {
      return this.$store.state.app.device;
    },
    fixedHeader() {
      return this.$store.state.settings.fixedHeader;
    },
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === "mobile"
      };
    },
    needTagsView() {
      return this.$store.state.settings.tagsView;
    }
  },
  mounted(){
    // window.addEventListener("scroll", this.handleScroll);
    // let aside = document.querySelector('.sidebar-container').style.width
    // this.width = (window.innerWidth - 210) + 'px'
  },
  methods: {
    // handleClickOutside() {
    //   this.$store.dispatch("app/closeSideBar", { withoutAnimation: false });
    // },
    // handleScroll () {
    //     let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
    //     if (scrollTop > 0) {
    //                   document.querySelector("#fixed-header").style.position = "fixed";

    
    //     } else {
    //                   document.querySelector("#fixed-header").style.position = "unset";
        
    //     }
    //   }
  },
  // destroyed () {
  //     window.removeEventListener("scroll", this.handleScroll);
  //   }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
@import "~@/styles/variables.scss";

.app-wrapper {
  @include clearfix;
  position: relative;
  height: 100%;
  width: 100%;
  &.mobile.openSidebar {
    position: fixed;
    top: 0;
  }
}
#fixed-header {
  z-index: 1000;
}
.drawer-bg {
  background: #000;
  opacity: 0.3;
  width: 100%;
  top: 0;
  height: 100%;
  position: absolute;
  z-index: 999;
}

.fixed-header {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 9;
  width: calc(100% - #{$sideBarWidth});
  transition: width 0.28s;
}

.hideSidebar .fixed-header {
  width: calc(100% - 54px);
}

.mobile .fixed-header {
  width: 100%;
}
</style>
