/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.alver.scryfall.api;

import com.alver.fatefall.api.models.scryfall.*;
import com.alver.scryfall.api.models.ErrorException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;

import java.util.UUID;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Cards.
 */
public interface Cards {
    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the CardList object if successful.
     */
    CardList search(String q);

    /**
     *
     * @param q the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<CardList> searchAsync(String q, final ServiceCallback<CardList> serviceCallback);

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    Observable<CardList> searchAsync(String q);

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    Observable<ServiceResponse<CardList>> searchWithServiceResponseAsync(String q);
    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the CardList object if successful.
     */
    CardList search(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page);

    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<CardList> searchAsync(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page, final ServiceCallback<CardList> serviceCallback);

    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    Observable<CardList> searchAsync(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page);

    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    Observable<ServiceResponse<CardList>> searchWithServiceResponseAsync(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getNamed();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getNamedAsync(final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getNamedAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getNamedWithServiceResponseAsync();
    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getNamed(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty);

    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getNamedAsync(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty, final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getNamedAsync(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty);

    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getNamedWithServiceResponseAsync(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty);

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    Catalog autocomplete(String q);

    /**
     *
     * @param q the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Catalog> autocompleteAsync(String q, final ServiceCallback<Catalog> serviceCallback);

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<Catalog> autocompleteAsync(String q);

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    Observable<ServiceResponse<Catalog>> autocompleteWithServiceResponseAsync(String q);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getRandom();

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getRandomAsync(final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getRandomAsync();

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getRandomWithServiceResponseAsync();

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getByMultiverseId(int id);

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getByMultiverseIdAsync(int id, final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getByMultiverseIdAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getByMultiverseIdWithServiceResponseAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getByMtgoId(int id);

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getByMtgoIdAsync(int id, final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getByMtgoIdAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getByMtgoIdWithServiceResponseAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getByArenaId(int id);

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getByArenaIdAsync(int id, final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getByArenaIdAsync(int id);

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getByArenaIdWithServiceResponseAsync(int id);

    /**
     *
     * @param code the String value
     * @param number the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getByCodeByNumber(String code, int number);

    /**
     *
     * @param code the String value
     * @param number the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getByCodeByNumberAsync(String code, int number, final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @param code the String value
     * @param number the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getByCodeByNumberAsync(String code, int number);

    /**
     *
     * @param code the String value
     * @param number the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getByCodeByNumberWithServiceResponseAsync(String code, int number);

    /**
     *
     * @param id the UUID value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    Card getById(UUID id);

    /**
     *
     * @param id the UUID value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    ServiceFuture<Card> getByIdAsync(UUID id, final ServiceCallback<Card> serviceCallback);

    /**
     *
     * @param id the UUID value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<Card> getByIdAsync(UUID id);

    /**
     *
     * @param id the UUID value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    Observable<ServiceResponse<Card>> getByIdWithServiceResponseAsync(UUID id);

}
