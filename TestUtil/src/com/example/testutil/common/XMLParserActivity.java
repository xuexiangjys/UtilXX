package com.example.testutil.common;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.common.entity.Person;
import com.thoughtworks.xstream.XStream;
import com.xuexiang.app.activity.BaseHeadActivity;
import com.xuexiang.util.common.StringUtil;
import com.xuexiang.util.common.convert.XmlHelper;
import com.xuexiang.util.net.JsonUtil;

/**
 * 创建时间：2017-4-2 下午11:06:01 项目名称：UtilTest
 * 
 * @author xuexiang 文件名称：XMLParserActivity.java
 **/
public class XMLParserActivity extends BaseHeadActivity implements OnClickListener{
	private EditText name, gender, age, address;
	private TextView result;
	private String xmlString;
	@Override
	protected int getLayoutId() {
		return R.layout.activity_xmlparser;
	}

	@Override
	protected void init() {
		name = (EditText) findViewById(R.id.name);
		gender = (EditText) findViewById(R.id.gender);
		age = (EditText) findViewById(R.id.age);
		address = (EditText) findViewById(R.id.address);
		result = (TextView) findViewById(R.id.result);
	}

	@Override
	public void onClick(View v) {
		Person person;
		XStream xstream = new XStream();
		switch (v.getId()) {
		case R.id.ObjecttoXML:
			person = new Person();
			person.setName(name.getText().toString());
			person.setGender(gender.getText().toString());
			person.setAge(age.getText().toString());
			person.setAddress(address.getText().toString());
			xstream.alias("Person", Person.class); // 修改别名
			xmlString = xstream.toXML(person);
			result.setText("ObjecttoXML结果：\n" + xmlString);
			break;
		case R.id.XMLtoObject:
			if (!StringUtil.isEmpty(xmlString)) {
				xstream.alias("Person", Person.class); // 修改别名
				person = (Person) xstream.fromXML(xmlString);
				result.setText("XMLtoObject结果：\n" + JsonUtil.toJson(person));
			}
			break;
		case R.id.ReadXMLtoObject:
			xstream.alias("Person", Person.class); // 修改别名
			person = (Person) xstream.fromXML(getResources().openRawResource(R.raw.person));
			result.setText("ReadXMLtoObject结果：\n" + JsonUtil.toJson(person));
			break;
		case R.id.xmlhelper:
			person = XmlHelper.getInstance().getObject(Person.class, getResources().openRawResource(R.raw.person), "Person");
			result.setText("XmlHelper结果：\n" + JsonUtil.toJson(person));
			break;

		default:
			break;
		}
	}

}
