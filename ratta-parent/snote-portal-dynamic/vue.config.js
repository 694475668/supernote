module.exports = {
    lintOnSave:false,
    baseUrl: './',
    devServer: {
        disableHostCheck: true,
        port: 9530,
        inline:false
        // proxy: {
        //     "/api": {
        //         "target": 'https://test-supernote.ratta.com.cn/invitation/clouddisk/Invitation',
        //         "changeOrigin": true,
        //         'secure': false,
        //         'pathRewrite': {
        //             '^/api': ''
        //         }
        //     }
        // }
    }
}
