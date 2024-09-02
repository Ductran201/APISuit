package ra.ecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.exception.CustomException;
import ra.ecommerceapi.model.entity.User;
import ra.ecommerceapi.repository.IRoleRepo;
import ra.ecommerceapi.repository.IUserRepo;
import ra.ecommerceapi.service.IRoleService;
import ra.ecommerceapi.service.IUserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepo userRepo;

    private final IRoleService roleService;

    @Override
    public Page<User> findAllPaginationAdmin(String search, Pageable pageable) {
        return userRepo.findAllByFullNameContainingExceptAdmin(search, pageable);
    }

    @Override
    public void addRoleForUser(Long userId, Long roleId) throws CustomException {
        User user = findUserExceptAdminById(userId);
        //check exist role
        if (user.getRoleSet().stream().anyMatch(r -> r.getId().equals(roleId))) {
            throw new CustomException("This user already has this role");
        }
        user.getRoleSet().add(roleService.findById(roleId));
        userRepo.save(user);
    }

    @Override
    public void deleteRoleForUser(Long userId, Long roleId) throws CustomException {
        User user = findUserExceptAdminById(userId);
        //check exist role
        if (user.getRoleSet().stream().anyMatch(r -> r.getId().equals(roleId))) {
            user.getRoleSet().remove(roleService.findById(roleId));
            userRepo.save(user);
        } else {
            throw new CustomException("This user has no this role");
        }
    }

    @Override
    public User findUserExceptAdminById(Long id) {
        return userRepo.findUserExceptAdminById(id).orElseThrow(() -> new NoSuchElementException("Not found this user"));
    }

    @Override
    public User save(User user) {
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
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Not found this user"));
    }


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


