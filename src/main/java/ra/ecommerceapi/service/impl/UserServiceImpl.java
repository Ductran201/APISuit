package ra.ecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.model.entity.User;
import ra.ecommerceapi.repository.IUserRepo;
import ra.ecommerceapi.service.IUserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findAllExceptAdmin() {
        return userRepo.findAllExceptAdmin();
    }

    @Override
    public User findUserExceptAdminById(Long id) {
        return userRepo.findUserExceptAdminById(id).orElseThrow(() -> new NoSuchElementException("Not found this user"));
    }

    @Override
    public User save(User user) {
        user.setStatus(true);
        return userRepo.save(user);
    }

    @Override
    public User save(User user, Long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Not found this user"));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public void toggleStatus(Long id) {
        userRepo.toggleStatus(id);
    }

    @Override
    public List<User> findAllByFullName(String fullName) {
        return userRepo.findAllByFullNameContaining(fullName);
    }

    @Override
    public Page<User> findAllPagination(Pageable pageable) {
        Pageable pageableCustom = PageRequest.of(pageable.getPageNumber(), 2, pageable.getSort());
        return userRepo.findAll(pageableCustom);

//        public PageDto findAll(Pageable pageable) {
//            Pageable custom = PageRequest.of(pageable.getPageNumber(),5,pageable.getSort());
//            Page<Student> page = studentRepository.findAll(custom);
//            return PageDto.builder().data(page.getContent())
//                    .pages(page.getTotalPages())
//                    .hasNext(page.hasNext())
//                    .hasPrev(page.hasPrevious())
//                    .size(page.getSize())
//                    .number(page.getNumber())
//                    .totalElements(page.getTotalElements())
//                    .sort(page.getSort())
//                    .build();


    }
}


