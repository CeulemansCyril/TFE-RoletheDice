package com.example.APIRollTheDice.Controllers.Game.Money;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.CurrencyDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Currency;
import com.example.APIRollTheDice.Service.Game.Money.CurrencyService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyServiceControllers {
    private final UserCreationContentService userCreationContentService;
    private final CurrencyService currencyService;

    public CurrencyServiceControllers(UserCreationContentService userCreationContentService, CurrencyService currencyService) {
        this.userCreationContentService = userCreationContentService;
        this.currencyService = currencyService;
    }

    @PostMapping("/CreateCurrency/{userId}")
    public ResponseEntity<CurrencyDTO> CreateCurrency(@PathVariable Long userId, @RequestBody CurrencyDTO currencyDTO) {
        Currency currency = currencyService.toEntity(currencyDTO);
        currency.setId(null);
         currency = currencyService.CreateCurrency(currency);

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setCreatedItemId(currency.getId());
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemType(CreatedItemType.CURRENCY);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(currencyService.toDTO(currency));
    }

    @PostMapping("/CreateManyCurrency/{userId}")
    public ResponseEntity<List<CurrencyDTO>> CreateManyCurrency(
            @PathVariable Long userId,
            @RequestBody List<CurrencyDTO> currencyDTOs)
    {
        List<Currency> currencies = currencyDTOs.stream()
                .map(currencyService::toEntity)
                .toList();

        for (Currency currency : currencies) {
            currency.setId(null);
        }

        currencies = currencyService.CreateManyCurrency(currencies);

        List<CurrencyDTO> result = new ArrayList<>();

        for (Currency currency : currencies) {

            UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
            userCreationContentDTO.setCreatedItemId(currency.getId());
            userCreationContentDTO.setUserId(userId);
            userCreationContentDTO.setCreatedItemType(CreatedItemType.CURRENCY);

            userCreationContentService.CreateUserCreationContent(
                    userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO)
            );

            result.add(currencyService.toDTO(currency));
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/UpdateCurrency")
    public ResponseEntity<CurrencyDTO> UpdateCurrency(@RequestBody CurrencyDTO currencyDTO) {
        Currency updatedCurrency = currencyService.toEntity(currencyDTO);
         updatedCurrency = currencyService.UpdateCurrency(updatedCurrency);
        return ResponseEntity.ok(currencyService.toDTO(updatedCurrency));
    }

    @PutMapping("/UpdateManyCurrency")
    public ResponseEntity<List<CurrencyDTO>>UpdateManyCurrency( @RequestBody List<CurrencyDTO> currencyDTOs)
    {
        List<Currency> currencies = currencyDTOs.stream()
                .map(currencyService::toEntity)
                .toList();


        List<CurrencyDTO> result = new ArrayList<>();

        currencies = currencyService.UpdateManyCurrency(currencies);
        result = currencies.stream().map(currencyService::toDTO).toList();

        return  ResponseEntity.ok(result);
    }

    @DeleteMapping("/DeleteCurrency/{currencyId}")
    public ResponseEntity<String> DeleteCurrency(@PathVariable Long currencyId) {
        currencyService.DeleteCurrency(currencyId);
        userCreationContentService.DeleteByCreatedItemId(currencyId,CreatedItemType.CURRENCY);
        return ResponseEntity.ok("Currency deleted successfully.");
    }

    @GetMapping("/GetAllCurrenciesByGameBundleId/{gameBundleId}")
    public ResponseEntity<List<CurrencyDTO>> GetAllCurrenciesByGameBundleId(@PathVariable Long gameBundleId) {
        List<Currency> currencies = currencyService.GetAllCurrencyByGameBundle(gameBundleId);
        List<CurrencyDTO> currencyDTOs = currencies.stream()
                .map(currencyService::toDTO)
                .toList();
        return ResponseEntity.ok(currencyDTOs);
    }


}
