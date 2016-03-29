package com.oneapm.model;

import java.util.List;


public class UserGroups {

        private Long groupId;
        private Long adminId;
        private String groupName;
        private Long parentId;
        private int deleted;
        
        private String createTime;
        private String language;
        private int level;
        
        private Long sale;
        private Long support;
        private Long preSale;
        private int payLevel;
        private String payTime;
        private String comming;
        private int emailStatus;
        private String contectTime;
        private String saleName;
        private String supportName;
        private String preSaleName;
        private String expireTime;
        private int daoqi;
        private String pay_level;
        private String project;
        private Long xiaoshouyi;
        private Long xiaoshouyiAdmin;
        private int companySize;
        
        public UserGroups(){
        
    }
        public UserGroups(Long groupId, Long adminId, String groupName, Long parentId, int deleted){
                setGroupId(groupId);
                setAdminId(adminId);
                setGroupName(groupName);
                setParentId(parentId);
                setDeleted(deleted);
        }
        public UserGroups(Long groupId, Long adminId, String groupName, Long parentId, int deleted, Long sale, Long support,
        		Long preSale, int payLevel, String payTime, String comming, int emailStatus, String contectTime){
            setGroupId(groupId);
            setAdminId(adminId);
            setGroupName(groupName);
            setParentId(parentId);
            setDeleted(deleted);
            setSale(sale);
            setSupport(support);
            setPreSale(preSale);
            setPayLevel(payLevel);
            setPayTime(payTime);
            setComming(comming);
            setEmailStatus(emailStatus);
            setContectTime(contectTime);
    }
        
       
        
		public Long getGroupId() {
                return groupId;
        }

        public void setGroupId(Long groupId) {
                this.groupId = groupId;
        }

        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }

        public String getGroupName() {
                return groupName;
        }

        public void setGroupName(String groupName) {
                this.groupName = groupName;
        }

        public Long getParentId() {
                return parentId;
        }

        public void setParentId(Long parentId) {
                this.parentId = parentId;
        }

        public int getDeleted() {
                return deleted;
        }

        public void setDeleted(int deleted) {
                this.deleted = deleted;
        }

        public Long getSale() {
                return sale;
        }

        public void setSale(Long sale) {
                this.sale = sale;
        }

        public Long getSupport() {
                return support;
        }

        public void setSupport(Long support) {
                this.support = support;
        }

        public Long getPreSale() {
                return preSale;
        }

        public void setPreSale(Long preSale) {
                this.preSale = preSale;
        }

        public int getPayLevel() {
                return payLevel;
        }

        public void setPayLevel(int payLevel) {
                this.payLevel = payLevel;
        }

        public String getPayTime() {
                return payTime;
        }

        public void setPayTime(String payTime) {
                this.payTime = payTime;
        }

        public String getComming() {
                return comming;
        }

        public void setComming(String comming) {
                this.comming = comming;
        }

        public int getEmailStatus() {
                return emailStatus;
        }

        public void setEmailStatus(int emailStatus) {
                this.emailStatus = emailStatus;
        }

        public String getContectTime() {
                return contectTime;
        }

        public void setContectTime(String contectTime) {
                this.contectTime = contectTime;
        }
		public String getSaleName() {
			return saleName;
		}
		public void setSaleName(String saleName) {
			this.saleName = saleName;
		}
		public String getSupportName() {
			return supportName;
		}
		public void setSupportName(String supportName) {
			this.supportName = supportName;
		}
		public String getPreSaleName() {
			return preSaleName;
		}
		public void setPreSaleName(String preSaleName) {
			this.preSaleName = preSaleName;
		}
		public String getExpireTime() {
			return expireTime;
		}
		public void setExpireTime(String expireTime) {
			this.expireTime = expireTime;
		}
		public int getDaoqi() {
			return daoqi;
		}
		public void setDaoqi(int daoqi) {
			this.daoqi = daoqi;
		}
		public String getPay_level() {
			return pay_level;
		}
		public void setPay_level(String pay_level) {
			this.pay_level = pay_level;
		}
        public String getCreateTime() {
                return createTime;
        }
        public void setCreateTime(String createTime) {
                this.createTime = createTime;
        }
        public String getProject() {
                return project;
        }
        public void setProject(String project) {
                this.project = project;
        }
		public Long getXiaoshouyi() {
			return xiaoshouyi;
		}
		public void setXiaoshouyi(Long xiaoshouyi) {
			this.xiaoshouyi = xiaoshouyi;
		}
		public Long getXiaoshouyiAdmin() {
			return xiaoshouyiAdmin;
		}
		public void setXiaoshouyiAdmin(Long xiaoshouyiAdmin) {
			this.xiaoshouyiAdmin = xiaoshouyiAdmin;
		}
        public String getLanguage() {
                return language;
        }
        public void setLanguage(String language) {
                this.language = language;
        }
        public int getLevel() {
                return level;
        }
        public void setLevel(int level) {
                this.level = level;
        }
        public int getCompanySize() {
                return companySize;
        }
        public void setCompanySize(int companySize) {
                this.companySize = companySize;
        }
        
		
        
}
