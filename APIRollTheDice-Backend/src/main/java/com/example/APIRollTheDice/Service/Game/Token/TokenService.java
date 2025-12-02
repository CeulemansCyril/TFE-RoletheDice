package com.example.APIRollTheDice.Service.Game.Token;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.GameBundleInterface;

import com.example.APIRollTheDice.Interface.GameInterface.MapInterface.LayoutInterface;
import com.example.APIRollTheDice.Interface.GameInterface.TemplateInterface.CustomObjectInterface;
import com.example.APIRollTheDice.Interface.GameInterface.TokenInterface.TokenInterface;
import com.example.APIRollTheDice.Interface.GameInterface.TokenInterface.TokenPlacedInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.Game.Token.TokenMapper;
import com.example.APIRollTheDice.Mapper.Game.Token.TokenPlacedMapper;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO.TokenDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO.TokenPlacedDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Token.Token;
import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {
    private final TokenInterface tokenInterface;
    private final TokenMapper tokenMapper;

    private final TokenPlacedInterface tokenPlacedInterface;
    private final TokenPlacedMapper tokenPlacedMapper;

    private final CustomObjectInterface customObjectInterface;
    private final UserRepository userRepository;
    private final GameBundleInterface gameInterface;
    private final LayoutInterface layoutInterface;

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

        tokenPlaced.setLayout(layoutInterface.findById(tokenPlacedDTO.getIdLayout()).orElseThrow(()-> new NotFoundException("Layout not found")));
        tokenPlaced.setToken(tokenInterface.findById(tokenPlacedDTO.getIdToken()).orElseThrow(()-> new NotFoundException("Token not found")));

        return tokenPlaced;
    }



}
