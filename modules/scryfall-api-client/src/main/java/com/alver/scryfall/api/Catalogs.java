/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.alver.scryfall.api;

import com.alver.fatefall.api.models.Catalog;
import com.alver.scryfall.api.models.ErrorException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import rx.Observable;


/**
 * An instance of this class provides access to all the operations defined
 * in Catalogs.
 */
public interface Catalogs {
    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getCardNames();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getCardNamesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getCardNamesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getCardNamesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getWordBank();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getWordBankAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getWordBankAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getWordBankWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getCreatureTypes();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getCreatureTypesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getCreatureTypesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getCreatureTypesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getPlaneswalkerTypes();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getPlaneswalkerTypesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getPlaneswalkerTypesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getPlaneswalkerTypesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getLandTypes();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getLandTypesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getLandTypesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getLandTypesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getArtifactTypes();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getArtifactTypesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getArtifactTypesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getArtifactTypesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getEnchantmentTypes();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getEnchantmentTypesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getEnchantmentTypesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getEnchantmentTypesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getSpellTypes();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getSpellTypesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getSpellTypesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getSpellTypesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getPowers();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getPowersAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getPowersAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getPowersWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getToughnesses();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getToughnessesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getToughnessesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getToughnessesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getLoyalties();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getLoyaltiesAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getLoyaltiesAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getLoyaltiesWithServiceResponseAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog getWatermarks();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> getWatermarksAsync(final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> getWatermarksAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> getWatermarksWithServiceResponseAsync();

}
