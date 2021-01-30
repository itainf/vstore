package com.vstore.vo;

import java.util.List;

/**
 * @author vstore
 */
public class TreeVo {
 private Long id;
 private String   text ;
 private String href;
 private String tags;
 private String icon;
 private List<TreeVo> nodes;
 private StateVo state;

    public StateVo getState() {
        return state;
    }

    public void setState(StateVo state) {
        this.state = state;
    }


    public enum MenuTypeEnum {


        /**
         * 菜单功能：按钮
         **/
        BUTTON( 2 ,"功能"),

        /**
         * 菜单功能：菜单
         **/
        MENU( 1 ,"菜单");


        private final Integer index;
        private final String name;


        MenuTypeEnum(Integer index, String name) {
            this.index = index;
            this.name = name;
        }


        public static String getName(Integer index){

            for(MenuTypeEnum accruedCleanEnum : MenuTypeEnum.values()){
                if(accruedCleanEnum.getIndex().equals(index)){
                    return accruedCleanEnum.name;
                }
            }
            return "";
        }


        public Integer getIndex() {
            return index;
        }


        public String getName() {
            return name;
        }

    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<TreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeVo> nodes) {
        this.nodes = nodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
