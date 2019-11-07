import Vue from 'vue'
import store from '../store'
/**
 * @export 自定义指令
 */
export function directive() {
    Vue.directive('permit', {
        bind(el, binding) {
            store.state.user.permission.findIndex(v => v.attributes.url === binding.value) !== -1 ? {} : el.style.display ='none'
        }
    })
}
