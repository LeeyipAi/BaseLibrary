package com.jmm.fx.test;


import java.util.List;

public class IntegralDetails {

    private String code;
    private Data data;

    public String getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }


    public static class Data {
        private List<Entity> list;
        private Pager pager;

        public List<Entity> getList() {
            return list;
        }

        public Pager getPager() {
            return pager;
        }

        public static class Pager {
            private String pageNo;
            private String pageSize;
            private String totalPage;

            public String getPageNo() {
                return pageNo;
            }

            public String getPageSize() {
                return pageSize;
            }

            public String getTotalPage() {
                return totalPage;
            }
        }

        public static class Entity {
            private String CHANNEL;
            private String ORDER_ID;
            private String USER_ID;
            private String INTEGRAL_NUM;
            private String LOCK_ID;
            private String ID;
            private String CREATE_DATE;
            private String isFreeze;

            public String getIsFreeze() {
                return isFreeze == null ? "0" : isFreeze;
            }

            public String getCHANNEL() {
                return CHANNEL;
            }

            public String getORDER_ID() {
                return ORDER_ID;
            }

            public String getUSER_ID() {
                return USER_ID;
            }

            public String getINTEGRAL_NUM() {
                return INTEGRAL_NUM;
            }

            public String getLOCK_ID() {
                return LOCK_ID;
            }

            public String getID() {
                return ID;
            }

            public String getCREATE_DATE() {
                return CREATE_DATE;
            }
        }
    }
}