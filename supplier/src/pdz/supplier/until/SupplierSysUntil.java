package pdz.supplier.until;

public class SupplierSysUntil {

	public static String getTool(int flag){
		String strtool="";
		if (flag==1) {
			strtool="toolbar: { items: ["
					+"{ id:'add',text: '新增', click: toolbarBtnItemClick, img: rootPath+'lib/icons/silkicons/add.png' },"
					+"{ id:'view',text: '查看', click: toolbarBtnItemClick, img: rootPath+'lib/icons/silkicons/application_view_detail.png' },"
					+"{ id:'delete',text: '删除', click: toolbarBtnItemClick, img: rootPath+'lib/icons/silkicons/delete.png'},"
					+"{ id:'modify',text: '修改故障信息', click: toolbarBtnItemClick, img: rootPath+'lib/icons/silkicons/application_edit.png' },"
					+"{ id:'modifysolve',text: '修改解决方案', click: toolbarBtnItemClick, img: rootPath+'lib/icons/silkicons/basket_edit.png' }"
					+"]}";
		}
		return strtool;
	}
}
