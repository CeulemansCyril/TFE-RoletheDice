package com.example.APIRollTheDice.Controllers.Game.Money;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.CurrencyDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Currency;
import com.example.APIRollTheDice.Service.Game.Money.CurrencyService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Currency currency = currencyService.CreateCurrency(currencyService.toEntity(currencyDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setCreatedItemId(currency.getId());
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemType(CreatedItemType.CURRENCY);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(currencyService.toDTO(currency));
    }

    @PutMapping("/UpdateCurrency")
    public ResponseEntity<CurrencyDTO> UpdateCurrency(@RequestBody CurrencyDTO currencyDTO) {
        Currency updatedCurrency = currencyService.UpdateCurrency(currencyService.toEntity(currencyDTO));
        return ResponseEntity.ok(currencyService.toDTO(updatedCurrency));
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
