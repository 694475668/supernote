说明：  
equipmentdb：  
1、t_task表和t_task_his表不迁移数据  
2、重新发布最后一个版本  

systembusinessdb：  
1、t_country_timezone在建表时就导入数据  
2、t_m_equiptype表的数据会迁移至t_dictionary表中（EQUIPMENT_MODEL）  

filedb：  
1、t_file_action、  
   t_file_convert、  
   t_file_his_sync、  
   t_recycle_file、  
   t_user_file，会按user_id取模15分库  
2、t_capacity 数据来源于用户表的总容量/该用户的实际容量  

systembasedb：  
1、t_pwd_his不迁移数据  
2、t_role、  
   t_user、  
   t_user_trole、  
   t_resource、  
   t_role_tresource，只迁移超管的数据  
3、t_m_resourcetype表的数据会迁移至t_dictionary表中（TRESOURCETYPE_ID）  
4、系统用户和角色需要自己创建及授权  
5、调度任务表业务码是file_clean和token_clean的不迁移，会将token_clean合并至数据清理  

****

数据库名称及编码：  
equipmentdb、    
systembasedb、  
systembusinessdb、  
userdb、  
filedb、  


字符集：utf8mb4
排序规则：utf8mb4_general_ci


****

迁移代码文档化  
一、	系统基础库  
1、	t_dictionary  
-	将原有数据迁移至新库  
-	将原有t_m_equiptype、t_m_resourcetype数据迁移至数据字典  
-	将数据字典所有数据初始化入redis  
2、	t_reference  
-	除了FILE_TYPE(以sql的形式insert到数据库)不迁移外，所有数据迁移至新库  
-	将参数所有数据初始化入redis  
3、	t_schedule_task  
-	file_clean和token_clean不迁移，其余迁移  
4、	t_schedule_log  
-	所有数据迁移至新库  
    5、将系统用户的数据初始化入redis  
二、	设备库  
1、	t_equipment  
-	将库存表中已激活的设备数据迁移至新库  
2、	t_equipment_log  
-	将原有数据迁移至新库  
3、	t_user_equipment  
-	将原有数据迁移至新库  
三、	用户库  
1、	t_commonly_area  
-	将原有数据迁移至新库  
2、	t_commonly_equipment  
-	将原有数据迁移至新库  
3、	t_login_record  
-	将原有数据迁移至新库  
4、	t_sensitive_record  
-	将原有数据迁移至新库  
5、	t_user  
-	将原有数据迁移至新库  
四、	文件库  
1、	t_capacity  
-   从t_user表查询总容量，从t_user_file表查询使用容量，将数据插入新库  
-   总条数和用户总数相同  
2、t_file_action  
   -   按user_id % 15取模，将数据迁移至15张表中  
3、t_file_his_sync  
-   按user_id % 15取模，将数据迁移至15张表中  
4、	t_recycle_file  
-   按user_id % 15取模，将数据迁移至15张表中  
5、	t_user_file  
-   按user_id % 15取模，将数据迁移至15张表中  
五、	系统业务库  
1、	t_app_fixpoint  
-	将原有数据迁移至新库  
2、	t_app_gray_deploy  
-	将原有数据迁移至新库  
3、	t_app_version  
-	将原有数据迁移至新库  
4、	t_dealer  
-	将原有数据迁移至新库  
5、	t_firmware_euip  
-	将原有数据迁移至新库  
6、	t_firmware_fixpoint  
-	将原有数据迁移至新库  
7、	t_firmware_info  
-	将原有数据迁移至新库  
8、	t_firmware_info_line  
-	将原有数据迁移至新库  
9、	t_firmware_task  
-	将原有数据迁移至新库  
-	不迁移审核测试任务  
-	将发布、刷机任务置为历史  
10、t_firmware_zip  
-	将原有数据迁移至新库  
11、t_return_record  
-	将原有数据迁移至新库  
12、t_stock  
-   将原有数据迁移至新库  
-   将task_id置为0  
-   将t_inout表的设备用途和经销商迁移至新库  
13、t_feedback_record  
-	将原有数据迁移至新库  
-	根据反馈的用户id查询绑定的设备列表，放置进新库  
14、t_feedback_type  
-	将原有数据迁移至新库  


