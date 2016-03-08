package com.guigu.mytime.buyticket.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class PersonInMovieBean {
    /**
     * persons : [{"id":892990,"image":"http://img21.mtime.cn/ph/2011/02/23/060746.59767667_1280X720X2.jpg","name":"叶伟信","nameEn":"Wilson Yip"}]
     * typeName : 导演
     * typeNameEn : Director
     */

    private List<TypesEntity> types;

    public void setTypes(List<TypesEntity> types) {
        this.types = types;
    }

    public List<TypesEntity> getTypes() {
        return types;
    }

    public static class TypesEntity {
        private String typeName;
        private String typeNameEn;
        /**
         * id : 892990
         * image : http://img21.mtime.cn/ph/2011/02/23/060746.59767667_1280X720X2.jpg
         * name : 叶伟信
         * nameEn : Wilson Yip
         */

        private List<PersonsEntity> persons;

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public void setTypeNameEn(String typeNameEn) {
            this.typeNameEn = typeNameEn;
        }

        public void setPersons(List<PersonsEntity> persons) {
            this.persons = persons;
        }

        public String getTypeName() {
            return typeName;
        }

        public String getTypeNameEn() {
            return typeNameEn;
        }

        public List<PersonsEntity> getPersons() {
            return persons;
        }

        public static class PersonsEntity {
            private int id;
            private String image;
            private String name;
            private String nameEn;
            private String roleCover;
            private String personate;

            public String getPersonate() {
                return personate;
            }

            public void setPersonate(String personate) {
                this.personate = personate;
            }

            public String getRoleCover() {
                return roleCover;
            }

            public void setRoleCover(String roleCover) {
                this.roleCover = roleCover;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNameEn(String nameEn) {
                this.nameEn = nameEn;
            }

            public int getId() {
                return id;
            }

            public String getImage() {
                return image;
            }

            public String getName() {
                return name;
            }

            public String getNameEn() {
                return nameEn;
            }
        }
    }
}
