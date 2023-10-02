package bg.caroffershub.model.dtos;

import javax.validation.constraints.NotBlank;

public class DeleteOfferDTO {

    @NotBlank
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public DeleteOfferDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

}
