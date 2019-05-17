package jdbc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BdosRealTimeController extends MsgContent {


	private static String tableName = "bdos_real_time_controller";
	private static String []  tableColumnsNames = {"orm_id" , "device_ip" , "policy_name", "is_ipv4" , "protection" , "direction" , "protection_state" , "doa" , "time_stamp" , "isTcp"};

	UUID id;
	private String deviceIp;
	private String policyName;
	private Integer isIpv4;
	private Integer protection;
	private Integer direction;
	private Integer protectionState;
	private Integer doa;
	private Timestamp timeStamp;
	private Integer isTcp;


	public BdosRealTimeController(String deviceIp, String policyName,int isIpv4, int protection, int direction, int protectionState,int doa,int isTcp) {
		id = UUID.randomUUID();
		this.deviceIp = deviceIp;
		this.policyName = policyName;
		this.isIpv4 = isIpv4;
		this.protection = protection;
		this.direction = direction;
		this.protectionState = protectionState;
		this.doa = doa;
		this.timeStamp = new Timestamp(System.currentTimeMillis());;
		this.isTcp = isTcp;
	}



	public static   String getTableName () {
		return tableName;
	}

	public  static String [] getColumnsNames () {
		return tableColumnsNames;
	}

	public  List <String> getCellsValuesAsStr () {
		ArrayList <String> cellsvalueStrs = new ArrayList <String> ();	
		cellsvalueStrs.add(id.toString());
		cellsvalueStrs.add(deviceIp.toString());
		cellsvalueStrs.add(policyName);
		cellsvalueStrs.add(isIpv4.toString());
		cellsvalueStrs.add(protection.toString());
		cellsvalueStrs.add(direction.toString());
		cellsvalueStrs.add(protectionState.toString());
		cellsvalueStrs.add(doa.toString());
		cellsvalueStrs.add(timeStamp.toString());
		cellsvalueStrs.add(isTcp.toString());

		return cellsvalueStrs;
	}



}
