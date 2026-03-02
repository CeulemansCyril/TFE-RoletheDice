using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.TokenDTO;
using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Model.Object.Game.Token;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEditor.PackageManager;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Token
{
     public class TokenService : ApiService
    {
        private CatchError onError;
        public TokenService() : base("token") { }

        // ------------------- Token        -------------------------
        public Awaitable<TokenDTO> CreateToken<TokenDTO>(TokenDTO token, long idUser)
        {
            return CreateAsync("/CreateToken/" + idUser, token);
        }
        public Awaitable<TokenDTO> UpdateToken<TokenDTO>(TokenDTO token)
        {
            return UpdateAsync("/UpdateToken ", token);
        }

        public Awaitable<string> DeleteToken(long id)
        {
            return DeleteAsync("/DeleteToken/" + id);
        }

        public Awaitable<TokenDTO> GetTokenById<TokenDTO>(long id)
        {
            return GetAsync<TokenDTO>("/GetTokenById/" + id);
        }

        public Awaitable<TokenDTO[]> GetAllByGameBundleId<TokenDTO>(long id)
        {
            return GetAllAsync<TokenDTO>("/GetAllByGameBundleId/" + id);
        }

         

        public Tokens TokenDTOToToken(TokenDTO dto)
        {
            Tokens token = new Tokens();
            token.Id = dto.Id;
            token.Name = dto.Name;
            token.ImageURL = dto.ImageURL;
            token.Type = dto.Type;
            token.CanMove = dto.CanMove;
            token.Owner = new Players() { Id = dto.IdOwner };
            token.CustomObject = new CustomObject() { Id = dto.IdFiche };
            return token;
        }

        public TokenDTO TokenToTokenDTO(Tokens token)
        {
            TokenDTO dto = new TokenDTO();
            dto.Id = token.Id;
            dto.Name = token.Name;
            dto.ImageURL = token.ImageURL;
            dto.Type = token.Type;
            dto.CanMove = token.CanMove;
            dto.IdOwner = token.Owner != null ? token.Owner.Id : 0;
            dto.IdFiche = token.CustomObject != null ? token.CustomObject.Id : 0;
            return dto;
        }

        // ------------------- Token Placed -------------------------
        public Awaitable<TokenPlacedDTO> CreateTokenPlaced<TokenPlacedDTO>(TokenPlacedDTO token)
        {
            return CreateAsync("/CreateTokenPlaced", token);
        }
        public Awaitable<TokenPlacedDTO> UpdateTokenPlaced<TokenPlacedDTO>(TokenPlacedDTO token)
        {
            return UpdateAsync("/UpdateTokenPlaced ", token);
        }

        public Awaitable<string> DeleteTokenPlaced(long id)
        {
            return DeleteAsync("/DeleteTokenPlaced/" + id);
        }

        public Awaitable<TokenPlacedDTO> GetTokenPlacedById<TokenPlacedDTO>(long id)
        {
            return GetAsync<TokenPlacedDTO>("/GetTokenPlacedById/" + id);
        }

        public Awaitable<TokenPlacedDTO[]> GetAllTokenPlacedByLayoutId<TokenPlacedDTO>(long id)
        {
            return GetAllAsync<TokenPlacedDTO>("/GetAllTokenPlacedByLayoutId/" + id);
        }
 

        public TokenPlaced TokenPlacedDTOToTokenPlaced(TokenPlacedDTO dto)
        {
            TokenPlaced tokenPlaced = new TokenPlaced();
            tokenPlaced.Id = dto.Id;
            tokenPlaced.PositionX = dto.PositionX;
            tokenPlaced.PositionY = dto.PositionY;
            tokenPlaced.Rotation = dto.Rotation;
            tokenPlaced.Scale = dto.Scale;
            tokenPlaced.Token = new Tokens() { Id = dto.IdToken };
            return tokenPlaced;
        }

        public TokenPlacedDTO TokenPlacedToTokenPlacedDTO(TokenPlaced tokenPlaced, long idLayout)
        {
            TokenPlacedDTO dto = new TokenPlacedDTO();
            dto.Id = tokenPlaced.Id;
            dto.PositionX = tokenPlaced.PositionX;
            dto.PositionY = tokenPlaced.PositionY;
            dto.Rotation = tokenPlaced.Rotation;
            dto.Scale = tokenPlaced.Scale;
            dto.IdToken = tokenPlaced.Token != null ? tokenPlaced.Token.Id : 0;
            dto.IdLayout = idLayout;
            return dto;
        }

    }
}
