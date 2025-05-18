package com.example.schoolspace.service;

import com.example.schoolspace.model.AbstractEntity;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractServices<T extends AbstractEntity> {

//    @Autowired
//    private T abstractEntityRepository;
//
//    public List<T> getAllEntities() {
//        return repository.findAll();
//    }
//
//    public Optional<Teacher> getTeacherById(int id) {
//        return teacherRepository.findById(id);
//    }
//
//    @Transactional
//    public Teacher save(Teacher teacher) {
//        return teacherRepository.save(teacher);
//    }
//
//    @Transactional
//    public void deleteStudent(Integer id) {
//        teacherRepository.deleteById(id);
//    }
//
//    @Transactional
//    public Teacher updateStudent(Integer id, Teacher teacher) {
//        return teacherRepository.findById(id)
//                .map(existingTeacher -> {
//                    existingTeacher.setName(teacher.getName());
//                    existingTeacher.setEmail(teacher.getEmail());
//                    existingTeacher.setAge(teacher.getAge());
//                    return teacherRepository.save(existingTeacher);
//                })
//                .orElseThrow(() -> new RuntimeException("not found"));
//    }
}
