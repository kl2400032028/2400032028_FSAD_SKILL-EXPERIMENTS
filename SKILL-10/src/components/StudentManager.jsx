import React, { useState } from 'react';
import './StudentManager.css';

const StudentManager = () => {
  // 2. Initial students array with at least 5 objects
  const initialStudents = [
    { id: 101, name: 'John Doe', course: 'Computer Science' },
    { id: 102, name: 'Jane Smith', course: 'Data Science' },
    { id: 103, name: 'Mike Johnson', course: 'Artificial Intelligence' },
    { id: 104, name: 'Sarah Williams', course: 'Web Development' },
    { id: 105, name: 'Robert Brown', course: 'Cyber Security' },
  ];

  // 3. useState to store students and newStudent object
  const [students, setStudents] = useState(initialStudents);
  const [newStudent, setNewStudent] = useState({ id: '', name: '', course: '' });

  // 4. Update the newStudent object using onChange events
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewStudent({ ...newStudent, [name]: value });
  };

  // 5. Add Student Button logic
  const addStudent = () => {
    if (!newStudent.id || !newStudent.name || !newStudent.course) {
      alert("Please fill in all fields!");
      return;
    }
    
    // Check if ID already exists
    if (students.find(s => s.id === parseInt(newStudent.id))) {
      alert("Student ID already exists!");
      return;
    }

    const studentToAdd = { 
      ...newStudent, 
      id: parseInt(newStudent.id) 
    };

    setStudents([...students, studentToAdd]);
    
    // Clear input fields
    setNewStudent({ id: '', name: '', course: '' });
  };

  // 7. Delete logic
  const deleteStudent = (id) => {
    const updatedStudents = students.filter(s => s.id !== id);
    setStudents(updatedStudents);
  };

  return (
    <div className="student-manager-container">
      <h1>🎓 Academic Student Portal</h1>
      
      <div className="add-student-form">
        <h2>Add New Student</h2>
        <div className="input-group">
          <input
            type="number"
            name="id"
            placeholder="Student ID"
            value={newStudent.id}
            onChange={handleInputChange}
          />
          <input
            type="text"
            name="name"
            placeholder="Student Name"
            value={newStudent.name}
            onChange={handleInputChange}
          />
          <input
            type="text"
            name="course"
            placeholder="Course Name"
            value={newStudent.course}
            onChange={handleInputChange}
          />
          <button onClick={addStudent} className="add-btn">Add Student</button>
        </div>
      </div>

      <div className="student-list-section">
        <h2>Enrolled Students</h2>
        {students.length === 0 ? (
          // 8. Display if list is empty
          <p className="no-data">No students available</p>
        ) : (
          <div className="table-wrapper">
            <table className="student-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Course</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {students.map((student) => (
                  <tr key={student.id}>
                    <td>{student.id}</td>
                    <td>{student.name}</td>
                    <td>{student.course}</td>
                    <td>
                      <button 
                        onClick={() => deleteStudent(student.id)} 
                        className="delete-btn"
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default StudentManager;
