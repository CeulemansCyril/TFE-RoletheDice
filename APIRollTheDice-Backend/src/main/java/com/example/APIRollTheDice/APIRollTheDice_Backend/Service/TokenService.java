package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameBundleInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.MapInterface.LayoutInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface.CustomObjectInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TokenInterface.TokenInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TokenInterface.TokenPlacedInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Token.TokenMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Token.TokenPlacedMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TokenDTO.TokenDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TokenDTO.TokenPlacedDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token.Token;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token.TokenPlaced;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {
    private TokenInterface tokenInterface;
    private TokenMapper tokenMapper;

    private TokenPlacedInterface tokenPlacedInterface;
    private TokenPlacedMapper tokenPlacedMapper;

    private CustomObjectInterface customObjectInterface;
    private UserRepository userRepository;
    private GameBundleInterface gameInterface;
    private LayoutInterface layoutInterface;

    public TokenService(TokenInterface tokenInterface, TokenMapper tokenMapper, TokenPlacedInterface tokenPlacedInterface, TokenPlacedMapper tokenPlacedMapper, CustomObjectInterface customObjectInterface, UserRepository userRepository, LayoutInterface layoutInterface, GameBundleInterface gameInterface) {
        this.tokenInterface = tokenInterface;
        this.tokenMapper = tokenMapper;
        this.tokenPlacedInterface = tokenPlacedInterface;
        this.tokenPlacedMapper = tokenPlacedMapper;
        this.customObjectInterface = customObjectInterface;
        this.userRepository = userRepository;
        this.layoutInterface = layoutInterface;
        this.gameInterface = gameInterface;
    }

    public Token CreateToken(Token token){
        return tokenInterface.save(token);
    }

    public Token UpdateToken(Token token){
        Token existing = tokenInterface.findById(token.getId()).orElseThrow(()-> new NotFoundException("Token not found"));

        existing.setCanMove(token.isCanMove());
        existing.setName(token.getName());
        existing.setFiche(token.getFiche());
        existing.setType(token.getType());
        existing.setGameBundle(token.getGameBundle());
        existing.setImageURL(token.getImageURL());
        existing.setOwner(token.getOwner());

        return tokenInterface.save(existing);
    }

    public void DeleteToken(Long id){
        if (tokenInterface.existsById(id))tokenInterface.deleteById(id);
        else throw new NotFoundException("Token not found");
    }

    public Token GetTokenById(Long id){
        return tokenInterface.findById(id).orElseThrow(()-> new NotFoundException("Token not found"));
    }

    public List<Token> GetAllByGameBundleId(Long id){
        return tokenInterface.findAllByGameBundle_id(id);
    }

    public TokenDTO TokenToDTO(Token token){
        return tokenMapper.toDTO(token);
    }

    public Token TokenDTOToEntity(TokenDTO tokenDTO){
        Token token = tokenMapper.toEntity(tokenDTO);

        token.setOwner(userRepository.findById(tokenDTO.getIdOwner()).get());
        token.setFiche(customObjectInterface.findById(tokenDTO.getIdFiche()).get());
        token.setGameBundle(gameInterface.findById(tokenDTO.getIdGameBundle()).get());

        return token;

    }
    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public TokenPlaced CreateTokenPlaced(TokenPlaced tokenPlaced){
        return tokenPlacedInterface.save(tokenPlaced);
    }

    public TokenPlaced UpdateTokenPlaced(TokenPlaced tokenPlaced){
        TokenPlaced existing = tokenPlacedInterface.findById(tokenPlaced.getId()).orElseThrow(()-> new NotFoundException("TokenPlaced not found"));

        existing.setToken(tokenPlaced.getToken());
        existing.setPositionY(tokenPlaced.getPositionY());
        existing.setPositionX(tokenPlaced.getPositionX());
        existing.setLayout(tokenPlaced.getLayout());
        existing.setRotation(tokenPlaced.getRotation());
        existing.setSawByEveryone(tokenPlaced.isSawByEveryone());
        existing.setScale(tokenPlaced.getScale());

        return tokenPlacedInterface.save(existing);
    }

    public void DeleteTokenPlaced(Long id){
        if(tokenPlacedInterface.existsById(id))tokenPlacedInterface.deleteById(id);
        else throw new NotFoundException("TokenPlaced not found");
    }

    public TokenPlaced GetTokenPlacedById(Long id){
        return tokenPlacedInterface.findById(id).orElseThrow(()-> new NotFoundException("TokenPlaced not found"));
    }

    public List<TokenPlaced> GetAllTokenPlacedByLayoutId(Long id){
        return tokenPlacedInterface.findAllByLayoutId(id);
    }

    public TokenPlacedDTO TokenPlacedToDTO(TokenPlaced tokenPlaced){
        return tokenPlacedMapper.toDTO(tokenPlaced);
    }

    public TokenPlaced TokenPlacedDTOToEntity(TokenPlacedDTO tokenPlacedDTO){
        TokenPlaced tokenPlaced = tokenPlacedMapper.toEntity(tokenPlacedDTO);

        tokenPlaced.setLayout(layoutInterface.findById(tokenPlacedDTO.getIdLayout()).get());
        tokenPlaced.setToken(tokenInterface.findById(tokenPlacedDTO.getIdToken()).get());

        return tokenPlaced;
    }



}
