package com.manager;

import java.util.Map;

import com.dataDeal.SelectData;

public class GetTable {
	//���ͼ���
	public Object[][] getFileStates(){
		//ͨ��SelectData���ѯͼ������ݿ���ͼ����Ϣ��
		//��ѯ���Ϊ
		String sql = "select * from ͼ��";
		SelectData sd = new SelectData();
		//���ղ��ҵ�������
		Map<String, Object> data = sd.select(sql);
	
		//ȡ���ݿ���ͼ�������Ϣ��������
		String[] columnNames = {
				"��������","ͼ�����ͱ��","����"
				,"ͼ����","����","����","�۸�",
				"ҳ��","�ִ���","�������","���ʱ��"
		};
		int count = ((Integer)(data.get("����"))).intValue();
		String[][] results = new String[count][columnNames.length];
		//����Map
		int j = 1;
		for(int i=0; i<count;i++){
			results[i][0] = (data.get("��������"+j)).toString();
			results[i][1] = (data.get("ͼ�����ͱ��"+j)).toString();
			results[i][2] = (data.get("����"+j)).toString();
			results[i][3] = (data.get("ͼ����"+j)).toString();
			results[i][4] = (data.get("����"+j)).toString();
			results[i][5] = (data.get("����"+j)).toString();
			results[i][6] = (data.get("�۸�"+j)).toString();
			results[i][7] = (data.get("ҳ��"+j)).toString();
			results[i][8] = (data.get("�ִ���"+j)).toString();
			results[i][9] = (data.get("�������"+j)).toString();
			results[i][10] = (data.get("���ʱ��"+j)).toString();
			j = j+1;
		}
		
		return results;
	}
	
	//��ö��߱�
	public Object[][] getReaderStates(){
		//ͨ��SelectData���ѯͼ������ݿ���ͼ����Ϣ��
		//��ѯ���Ϊ
		String sql = "select * from ����";
		SelectData sd = new SelectData();
		//���ղ��ҵ�������
		Map<String, Object> data = sd.select(sql);
	
		//ȡ���ݿ���ͼ�������Ϣ��������
		String[] columnNames = {
				"����֤���","����","�Ա�","��������","���֤��",
				"ͼ����Ĵ���","�Ƿ��ʧ","�ɽ����","�ѽ����",
				"δ��������","����"
		};
		int count = ((Integer)(data.get("����"))).intValue();
		String[][] results = new String[count][columnNames.length];
		//����Map
		int j = 1;
		for(int i=0; i<count;i++){
			results[i][0] = (data.get("����֤���"+j)).toString();
			results[i][1] = (data.get("����"+j)).toString();
			results[i][2] = (data.get("�Ա�"+j)).toString();
			results[i][3] = (data.get("��������"+j)).toString();
			results[i][4] = (data.get("���֤��"+j)).toString();
			results[i][5] = (data.get("ͼ����Ĵ���"+j)).toString();
			results[i][6] = (data.get("�Ƿ��ʧ"+j)).toString();
			results[i][7] = (data.get("�ɽ����"+j)).toString();
			results[i][8] = (data.get("�ѽ����"+j)).toString();
			results[i][9] = (data.get("δ��������"+j)).toString();
			results[i][10] = (data.get("����"+j)).toString();
			j = j+1;
		}
		
		return results;
	}
	
	//�������
	public Object[][] getStoreStates(){
		//ͨ��SelectData���ѯͼ������ݿ���ͼ����Ϣ��
		//��ѯ���Ϊ
		String sql = "select * from ���";
		SelectData sd = new SelectData();
		//���ղ��ҵ�������
		Map<String, Object> data = sd.select(sql);
	
		//ȡ���ݿ���ͼ�������Ϣ��������
		String[] columnNames = {
				"����","�����"
		};
		int count = ((Integer)(data.get("����"))).intValue();
		String[][] results = new String[count][columnNames.length];
		//����Map
		int j = 1;
		for(int i=0; i<count;i++){
			results[i][0] = (data.get("����"+j)).toString();
			results[i][1] = (data.get("�����"+j)).toString();
			j = j+1;
		}
		
		return results;
	}
}
