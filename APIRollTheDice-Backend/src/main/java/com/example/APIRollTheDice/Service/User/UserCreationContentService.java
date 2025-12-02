package com.example.APIRollTheDice.Service.User;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.User.UserCreationContentInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.User.UserCreationContentMapper;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.User.UserCreationContent;
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

    public void DeleteAllUserCreationContentByUser(Long idUser){
        List<UserCreationContent> userCreationContents = userCreationContentInterface.findAllByUser_Id(idUser);
        userCreationContentInterface.deleteAll(userCreationContents);
    }

    public void DeleteByCreatedItemId(Long idCreatedItem, CreatedItemType createdItemType){
        userCreationContentInterface.deleteByCreatedItemIdAndCreatedItemType(idCreatedItem,createdItemType);
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
