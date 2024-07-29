package br.com.thiagofspaiva.urbanape.modules.user.services.impl;
import br.com.thiagofspaiva.urbanape.exceptions.DataAlreadyExistsException;
import br.com.thiagofspaiva.urbanape.exceptions.NotFoundException;
import br.com.thiagofspaiva.urbanape.modules.user.models.UserEntity;
import br.com.thiagofspaiva.urbanape.modules.user.dto.UserResponseDTO;
import br.com.thiagofspaiva.urbanape.modules.user.repository.UserRepository;
import br.com.thiagofspaiva.urbanape.modules.user.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;

    final private PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    }
    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::ToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO create(UserEntity entity) {
        emailExists(entity.getEmail());
        System.out.println(entity);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);


        return UserResponseDTO.ToDto(entity);
    }

    @Override
    public UserResponseDTO update(UUID id, UserEntity entity) {
        var user = findById(id);
        emailExists(entity.getEmail());

        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setPassword(passwordEncoder.encode(entity.getPassword()));

        userRepository.save(user);

        return UserResponseDTO.ToDto(user);

    }

    @Override
    public void delete(UUID id) {
        UserEntity user = findById(id);
        userRepository.delete(user);
    }

    public void emailExists(String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new DataAlreadyExistsException("O email já está em uso.");
        }
    }

}
