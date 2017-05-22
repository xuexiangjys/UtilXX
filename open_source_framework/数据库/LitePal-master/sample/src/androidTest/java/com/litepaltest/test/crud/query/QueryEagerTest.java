package com.litepaltest.test.crud.query;

import java.util.Calendar;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.test.AndroidTestCase;

import com.litepaltest.model.Classroom;
import com.litepaltest.model.IdCard;
import com.litepaltest.model.Student;
import com.litepaltest.model.Teacher;

public class QueryEagerTest extends AndroidTestCase {

	private Classroom classroom;

	private Student student1;

	private Student student2;
	
	private Student student3;

	private Teacher teacher1;

	private Teacher teacher2;

	private IdCard idcard1;

	private IdCard idcard2;

	@Override
	protected void setUp() {
		Calendar calendar = Calendar.getInstance();
		classroom = new Classroom();
		classroom.setName("Classroom 11");
		idcard1 = new IdCard();
		idcard1.setNumber("320311");
		idcard2 = new IdCard();
		idcard2.setNumber("320322");
		calendar.clear();
		calendar.set(1990, 9, 16, 0, 0, 0);
		student1 = new Student();
		student1.setName("Student 1");
		student1.setClassroom(classroom);
		student1.setIdcard(idcard1);
		student1.setBirthday(calendar.getTime());
		calendar.clear();
		calendar.set(1989, 7, 7, 0, 0, 0);
		student2 = new Student();
		student2.setName("Student 2");
		student2.setClassroom(classroom);
		student2.setBirthday(calendar.getTime());
		student3 = new Student();
		student3.setName("Student 3");
		teacher1 = new Teacher();
		teacher1.setTeacherName("Teacher 1");
		teacher1.setTeachYears(3);
		teacher1.setIdCard(idcard2);
		teacher2 = new Teacher();
		teacher2.setSex(false);
		teacher2.setTeacherName("Teacher 2");
		student1.getTeachers().add(teacher1);
		student1.getTeachers().add(teacher2);
		student2.getTeachers().add(teacher2);
		classroom.getTeachers().add(teacher1);
		classroom.save();
		student1.save();
		student2.save();
		student3.save();
		idcard1.save();
		idcard2.save();
		teacher1.save();
		teacher2.save();
	}

	public void testEagerFind() {
		Student s1 = DataSupport.find(Student.class, student1.getId(), true);
		Classroom c = s1.getClassroom();
		IdCard ic = s1.getIdcard();
		List<Teacher> tList = s1.getTeachers();
		assertNotNull(c);
		assertNotNull(ic);
		assertEquals(classroom.get_id(), c.get_id());
		assertEquals("Classroom 11", c.getName());
		assertEquals(idcard1.getId(), ic.getId());
		assertEquals("320311", ic.getNumber());
		assertEquals(student1.getTeachers().size(), tList.size());
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(1990, 9, 16, 0, 0, 0);
		assertEquals(calendar.getTime().getTime(), s1.getBirthday().getTime());
		for (Teacher t : tList) {
			if (t.getId() == teacher1.getId()) {
				assertEquals("Teacher 1", t.getTeacherName());
				assertEquals(teacher1.getTeachYears(), t.getTeachYears());
				assertTrue(t.isSex());
				continue;
			}
			if (t.getId() == teacher2.getId()) {
				assertEquals("Teacher 2", t.getTeacherName());
				assertFalse(t.isSex());
				continue;
			}
			fail();
		}
		s1 = DataSupport.find(Student.class, student1.getId());
		c = s1.getClassroom();
		assertNull(c);
		assertNull(s1.getIdcard());
		assertEquals(0, s1.getTeachers().size());
		c = DataSupport.find(Classroom.class, classroom.get_id(), true);
		assertEquals(2, c.getStudentCollection().size());
		assertEquals(1, c.getTeachers().size());
		for (Student s : c.getStudentCollection()) {
			if (s.getId() == student1.getId()) {
				assertEquals("Student 1", s.getName());
				continue;
			}
			if (s.getId() == student2.getId()) {
				assertEquals("Student 2", s.getName());
				calendar.clear();
				calendar.set(1989, 7, 7, 0, 0, 0);
				assertEquals(calendar.getTime().getTime(), s.getBirthday().getTime());
				continue;
			}
			fail();
		}
		Teacher t1 = DataSupport.find(Teacher.class, teacher2.getId(), true);
		List<Student> sList = t1.getStudents();
		assertEquals(teacher2.getStudents().size(), sList.size());
		for (Student s : sList) {
			if (s.getId() == student1.getId()) {
				assertEquals("Student 1", s.getName());
				calendar.clear();
				calendar.set(1990, 9, 16, 0, 0, 0);
				assertEquals(calendar.getTime().getTime(), s.getBirthday().getTime());
				continue;
			}
			if (s.getId() == student2.getId()) {
				assertEquals("Student 2", s.getName());
				continue;
			}
			fail();
		}
		Student s3 = DataSupport.find(Student.class, student3.getId());
		assertNull(s3.getBirthday());
	}

	public void resetData() {
		DataSupport.deleteAll(Student.class);
		DataSupport.deleteAll(Classroom.class);
		DataSupport.deleteAll(Teacher.class);
		DataSupport.deleteAll(IdCard.class);
		setUp();
	}

	public void testEagerFindFirst() {
		resetData();
		Student s1 = DataSupport.findFirst(Student.class);
		assertNull(s1.getClassroom());
		s1 = DataSupport.findFirst(Student.class, true);
		assertNotNull(s1);
	}

	public void testEagerFindLast() {
		resetData();
		Teacher t1 = DataSupport.findLast(Teacher.class);
		assertEquals(0, t1.getStudents().size());
		t1 = DataSupport.findLast(Teacher.class, true);
		assertTrue(0 < t1.getStudents().size());
	}

	public void testEagerFindAll() {
		resetData();
		List<Student> sList = DataSupport.findAll(Student.class);
		for (Student s : sList) {
			assertNull(s.getClassroom());
			assertEquals(0, s.getTeachers().size());
		}
		sList = DataSupport.findAll(Student.class, true);
		for (Student s : sList) {
			if (s.getClassroom() == null) {
				continue;
			}
			assertEquals("Classroom 11", s.getClassroom().getName());
			assertTrue(s.getTeachers().size() > 0);
			List<Teacher> tList = s.getTeachers();
			for (Teacher t : tList) {
				if (t.getId() == teacher1.getId()) {
					assertEquals("Teacher 1", t.getTeacherName());
					assertEquals(teacher1.getTeachYears(), t.getTeachYears());
					assertTrue(t.isSex());
					continue;
				}
				if (t.getId() == teacher2.getId()) {
					assertEquals("Teacher 2", t.getTeacherName());
					assertFalse(t.isSex());
					continue;
				}
				fail();
			}
		}
	}

	public void testEagerClusterQuery() {
		resetData();
		List<Student> sList = DataSupport.where("id = ?", String.valueOf(student1.getId())).find(
				Student.class);
		assertEquals(1, sList.size());
		Student s = sList.get(0);
		assertNull(s.getClassroom());
		sList = DataSupport.where("id = ?", String.valueOf(student1.getId())).find(Student.class, true);
		assertEquals(1, sList.size());
		s = sList.get(0);
		assertNotNull(s.getClassroom());
		Classroom c = s.getClassroom();
		assertEquals("Classroom 11", c.getName());
	}

}