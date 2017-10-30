#sql("findRoleList")
   select * from system_role where 1=1
   #if(roleName)
      and role_name = #para(roleName)
   #end
#end