package com.example.APIRollTheDice.APIRollTheDice_Backend.Service.User;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User.UserCreationContentInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.User.UserCreationContentMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.UserCreationContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCreationContentService {
    private final UserCreationContentInterface userCreationContentInterface;
    private final UserCreationContentMapper userCreationContentMapper;

    private final UserRepository userRepository;

    public UserCreationContentService(UserCreationContentInterface userCreationContentInterface, UserCreationContentMapper userCreationContentMapper, UserRepository userRepository) {
        this.userCreationContentInterface = userCreationContentInterface;
        this.userCreationContentMapper = userCreationContentMapper;
        this.userRepository = userRepository;
    }

    public UserCreationContent CreateUserCreationContent(UserCreationContent userCreationContent){
        return userCreationContentInterface.save(userCreationContent);
    }

   public void DeleteUserCreationContent(Long id){
        userCreationContentInterface.deleteById(id);
    }

    public List<UserCreationContent> GetAllUserCreationContentByUser(Long idUser){
        return userCreationContentInterface.findAllByUser_Id(idUser);
    }

    public UserCreationContent getUserCreationContentById(Long id){
        return userCreationContentInterface.findById(id).orElseThrow(()-> new NotFoundException("UserCreationContent not found"));
    }


    public UserCreationContent UserCreationContentDTOToEntity(UserCreationContentDTO userCreationContent){
        UserCreationContent userCreationContentEntity = userCreationContentMapper.toEntity(userCreationContent);
        if(userCreationContent.getUserId() != null){
            userCreationContentEntity.setUser(userRepository.findById(userCreationContent.getUserId()).
                    orElseThrow(() -> new NotFoundException("User not found"))
            );
        }
        return userCreationContentEntity;
    }

    public UserCreationContentDTO UserCreationContentToDTO(UserCreationContent userCreationContent){
        return userCreationContentMapper.toDTO(userCreationContent);
    }



}
