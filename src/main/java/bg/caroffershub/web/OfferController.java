package bg.caroffershub.web;

import bg.caroffershub.model.dtos.AddOfferDTO;
import bg.caroffershub.model.dtos.DeleteOfferDTO;
import bg.caroffershub.model.dtos.OfferDetailsDTO;
import bg.caroffershub.model.dtos.SearchOfferDTO;
import bg.caroffershub.service.BrandService;
import bg.caroffershub.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final BrandService brandService;
    private final OfferService offerService;

    @Autowired
    public OfferController(BrandService brandService, OfferService offerService) {
        this.brandService = brandService;
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String allOffers(Model model, @PageableDefault(
            sort = "price",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 5) Pageable pageable) {

        Page<OfferDetailsDTO> allOffersPaged = this.offerService.getAllOffers(pageable);
        model.addAttribute("offers", allOffersPaged);

        return "offers";
    }

    @GetMapping("/add")
    public String offersAdd(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new AddOfferDTO());
        }

        model.addAttribute("brands", this.brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String offersAdd(@Valid AddOfferDTO addOfferModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            //here we can use Principal object as well
                            @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);

            return "redirect:/offers/add";
        }

        this.offerService.addOffer(addOfferModel, userDetails);

        return "redirect:/offers/all";
    }

    @GetMapping("/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel", bindingResult);
            return "offer-search";
        }

        if (!model.containsAttribute("searchOfferModel")) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
        }

        if (!searchOfferDTO.isEmpty()) {
            model.addAttribute("offers", this.offerService.searchOffer(searchOfferDTO));
        }

        return "offer-search";
    }

    @GetMapping("/{uuid}/details")
    public String offerDetails(@PathVariable("uuid") UUID id,
                               Model model) {

        OfferDetailsDTO offerById = this.offerService.findOfferById(id);

        if (!model.containsAttribute("offer")) {
            model.addAttribute("offer", offerById);
        }

        return "offer-details";
    }

    @GetMapping("/delete")
    public String deleteOffer(Model model) {
        if (!model.containsAttribute("deleteOfferModel")) {
            model.addAttribute("deleteOfferModel", new DeleteOfferDTO());
        }

        return "offer-delete";
    }

    @PostMapping("/delete")
    public String deleteOffer(@Valid DeleteOfferDTO deleteOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("deleteOfferModel", deleteOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deleteOfferModel", bindingResult);

            return "redirect:/offers/delete";
        }

        this.offerService.deleteOfferById(deleteOfferDTO);

        return "redirect:/offers/all";
    }

}
