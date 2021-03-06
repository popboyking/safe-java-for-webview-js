/**
 * Summary: 异步回调页面JS函数管理对象
 * Version 1.0
 * Author: zhaomi@jugame.com.cn
 * Company: www.mjgame.cn
 * Date: 13-11-26
 * Time: 下午7:55
 * Copyright: Copyright (c) 2013
 */

package cn.pedant.SafeJava4WebviewJS.webview.bridge;

import android.webkit.WebView;
import cn.pedant.SafeJava4WebviewJS.util.Log;
import cn.pedant.SafeJava4WebviewJS.webview.BaseWebView;


public class JsCallback {
    private static final String CALLBACK_JS_FORMAT = "javascript:HostApp.callback(%d, %d %s);";
    private int index;
    private BaseWebView baseWebView;
    private int isPermanent;

    public JsCallback (final WebView view, int index) {
        this.index = index;
        this.baseWebView = (BaseWebView)view;
    }

    public void apply (Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args){
            sb.append(",");
            boolean isStrArg = arg instanceof String;
            if (isStrArg) {
                sb.append("\"");
            }
            sb.append(String.valueOf(arg));
            if (isStrArg) {
                sb.append("\"");
            }
        }
        String execJs = String.format(CALLBACK_JS_FORMAT, index, isPermanent, sb.toString());
        Log.d("[JsCallBack] " + execJs);
        baseWebView.loadJS(execJs);
    }

    public void setPermanent (boolean value) {
        isPermanent = value ? 1 : 0;
    }
}