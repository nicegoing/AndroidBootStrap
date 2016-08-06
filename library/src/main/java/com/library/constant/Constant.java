package com.library.constant;

/**
 * 常用的常量
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/6
 * @since 1.0
 */
public class Constant {
    public static class DIALOG_TYPE {
        /**
         * 加载对话框
         */
        public final static int LOADING_DIALOG = 1;
        /**
         * 确认对话框,显示一条消息，根据用户选择返回特定值
         */
        public final static int CONFIRM_DIALOG = 2;

        /**
         * 提示对话框,获取用户特定输入值
         */
        public final static int PROMPT_DIALOG = 3;
        /**
         * 其他对话框
         */
        public final static int OTHER_DIALOG  = 4;
    }
}
