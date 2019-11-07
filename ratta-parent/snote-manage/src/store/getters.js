const getters = {
    sidebar: state => state.app.sidebar,
    device: state => state.app.device,
    token: state => state.user.token,
    avatar: state => state.user.avatar,
    name: state => state.user.name,
    permission_routes: state => state.permission.routes,
    visitedViews: state => state.tagsView.visitedViews,
    routers: state => state.user.routers
}
export default getters