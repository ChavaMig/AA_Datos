# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**clinicsGet**](DefaultApi.md#clinicsGet) | **GET** /clinics | Devuelve la lista de clínicas |
| [**clinicsIdDelete**](DefaultApi.md#clinicsIdDelete) | **DELETE** /clinics/{id} | Eliminar una clínica |
| [**clinicsIdGet**](DefaultApi.md#clinicsIdGet) | **GET** /clinics/{id} | Buscar clínica por ID |
| [**clinicsIdPut**](DefaultApi.md#clinicsIdPut) | **PUT** /clinics/{id} | Modificar una clínica |
| [**clinicsPost**](DefaultApi.md#clinicsPost) | **POST** /clinics | Registrar una clínica |
| [**machinesGet**](DefaultApi.md#machinesGet) | **GET** /machines | Devuelve la lista de máquinas |
| [**machinesIdDelete**](DefaultApi.md#machinesIdDelete) | **DELETE** /machines/{id} | Eliminar una máquina |
| [**machinesIdGet**](DefaultApi.md#machinesIdGet) | **GET** /machines/{id} | Buscar máquina por ID |
| [**machinesIdPut**](DefaultApi.md#machinesIdPut) | **PUT** /machines/{id} | Modificar una máquina |
| [**machinesPost**](DefaultApi.md#machinesPost) | **POST** /machines | Registrar una máquina |
| [**sparePartsGet**](DefaultApi.md#sparePartsGet) | **GET** /spare-parts | Devuelve la lista de repuestos |
| [**sparePartsIdDelete**](DefaultApi.md#sparePartsIdDelete) | **DELETE** /spare-parts/{id} | Eliminar un repuesto |
| [**sparePartsIdGet**](DefaultApi.md#sparePartsIdGet) | **GET** /spare-parts/{id} | Buscar repuesto por ID |
| [**sparePartsIdPut**](DefaultApi.md#sparePartsIdPut) | **PUT** /spare-parts/{id} | Modificar un repuesto |
| [**sparePartsPost**](DefaultApi.md#sparePartsPost) | **POST** /spare-parts | Registrar un nuevo repuesto |
| [**techniciansGet**](DefaultApi.md#techniciansGet) | **GET** /technicians | Devuelve la lista de técnicos |
| [**techniciansIdDelete**](DefaultApi.md#techniciansIdDelete) | **DELETE** /technicians/{id} | Eliminar un técnico |
| [**techniciansIdGet**](DefaultApi.md#techniciansIdGet) | **GET** /technicians/{id} | Buscar técnico por ID |
| [**techniciansIdPut**](DefaultApi.md#techniciansIdPut) | **PUT** /technicians/{id} | Modificar un técnico |
| [**techniciansPost**](DefaultApi.md#techniciansPost) | **POST** /technicians | Registrar un técnico |
| [**ticketsGet**](DefaultApi.md#ticketsGet) | **GET** /tickets | Devuelve la lista de tickets |
| [**ticketsIdDelete**](DefaultApi.md#ticketsIdDelete) | **DELETE** /tickets/{id} | Eliminar un ticket |
| [**ticketsIdGet**](DefaultApi.md#ticketsIdGet) | **GET** /tickets/{id} | Buscar ticket por ID |
| [**ticketsIdPut**](DefaultApi.md#ticketsIdPut) | **PUT** /tickets/{id} | Modificar un ticket |
| [**ticketsPost**](DefaultApi.md#ticketsPost) | **POST** /tickets | Registrar un ticket de avería |


<a id="clinicsGet"></a>
# **clinicsGet**
> List&lt;ClinicOutDto&gt; clinicsGet()

Devuelve la lista de clínicas

Devuelve una lista con todas las clínicas registradas.  Muestra la información básica a través de **ClinicOutDto**. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<ClinicOutDto> result = apiInstance.clinicsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#clinicsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ClinicOutDto&gt;**](ClinicOutDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **500** | Internal server error |  -  |

<a id="clinicsIdDelete"></a>
# **clinicsIdDelete**
> clinicsIdDelete(id)

Eliminar una clínica

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      apiInstance.clinicsIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#clinicsIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Clínica eliminada correctamente (No Content) |  -  |

<a id="clinicsIdGet"></a>
# **clinicsIdGet**
> ClinicDto clinicsIdGet(id)

Buscar clínica por ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | ID único de la clínica
    try {
      ClinicDto result = apiInstance.clinicsIdGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#clinicsIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID único de la clínica | |

### Return type

[**ClinicDto**](ClinicDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Clínica encontrada |  -  |
| **404** | Not Found - La clínica no existe |  -  |

<a id="clinicsIdPut"></a>
# **clinicsIdPut**
> clinicsIdPut(id, clinic)

Modificar una clínica

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    Clinic clinic = new Clinic(); // Clinic | 
    try {
      apiInstance.clinicsIdPut(id, clinic);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#clinicsIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **clinic** | [**Clinic**](Clinic.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Clínica actualizada correctamente |  -  |
| **404** | No encontrada |  -  |

<a id="clinicsPost"></a>
# **clinicsPost**
> Clinic clinicsPost(clinic)

Registrar una clínica

Registra una nueva clínica validando que el nombre no esté vacío.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Clinic clinic = new Clinic(); // Clinic | Datos de la nueva clínica (id no es necesario enviarlo)
    try {
      Clinic result = apiInstance.clinicsPost(clinic);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#clinicsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **clinic** | [**Clinic**](Clinic.md)| Datos de la nueva clínica (id no es necesario enviarlo) | |

### Return type

[**Clinic**](Clinic.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Clínica registrada con éxito |  -  |
| **400** | Bad request - Error de validación (NotBlank) |  -  |
| **500** | Internal server error |  -  |

<a id="machinesGet"></a>
# **machinesGet**
> List&lt;Machine&gt; machinesGet()

Devuelve la lista de máquinas

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<Machine> result = apiInstance.machinesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#machinesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Machine&gt;**](Machine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="machinesIdDelete"></a>
# **machinesIdDelete**
> machinesIdDelete(id)

Eliminar una máquina

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      apiInstance.machinesIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#machinesIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Máquina eliminada |  -  |

<a id="machinesIdGet"></a>
# **machinesIdGet**
> Machine machinesIdGet(id)

Buscar máquina por ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Machine result = apiInstance.machinesIdGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#machinesIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

[**Machine**](Machine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Máquina no encontrada |  -  |

<a id="machinesIdPut"></a>
# **machinesIdPut**
> machinesIdPut(id, machine)

Modificar una máquina

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    Machine machine = new Machine(); // Machine | 
    try {
      apiInstance.machinesIdPut(id, machine);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#machinesIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **machine** | [**Machine**](Machine.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Máquina actualizada |  -  |

<a id="machinesPost"></a>
# **machinesPost**
> machinesPost(machine)

Registrar una máquina

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Machine machine = new Machine(); // Machine | 
    try {
      apiInstance.machinesPost(machine);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#machinesPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **machine** | [**Machine**](Machine.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Máquina registrada |  -  |
| **400** | Bad request |  -  |

<a id="sparePartsGet"></a>
# **sparePartsGet**
> List&lt;SparePart&gt; sparePartsGet()

Devuelve la lista de repuestos

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<SparePart> result = apiInstance.sparePartsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#sparePartsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;SparePart&gt;**](SparePart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="sparePartsIdDelete"></a>
# **sparePartsIdDelete**
> sparePartsIdDelete(id)

Eliminar un repuesto

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      apiInstance.sparePartsIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#sparePartsIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Repuesto eliminado |  -  |

<a id="sparePartsIdGet"></a>
# **sparePartsIdGet**
> SparePart sparePartsIdGet(id)

Buscar repuesto por ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      SparePart result = apiInstance.sparePartsIdGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#sparePartsIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

[**SparePart**](SparePart.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Repuesto no encontrado |  -  |

<a id="sparePartsIdPut"></a>
# **sparePartsIdPut**
> sparePartsIdPut(id, sparePart)

Modificar un repuesto

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    SparePart sparePart = new SparePart(); // SparePart | 
    try {
      apiInstance.sparePartsIdPut(id, sparePart);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#sparePartsIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **sparePart** | [**SparePart**](SparePart.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Repuesto actualizado |  -  |

<a id="sparePartsPost"></a>
# **sparePartsPost**
> sparePartsPost(sparePart)

Registrar un nuevo repuesto

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    SparePart sparePart = new SparePart(); // SparePart | 
    try {
      apiInstance.sparePartsPost(sparePart);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#sparePartsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **sparePart** | [**SparePart**](SparePart.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Repuesto creado |  -  |
| **400** | Error de validación (BadRequest) |  -  |

<a id="techniciansGet"></a>
# **techniciansGet**
> List&lt;Technician&gt; techniciansGet()

Devuelve la lista de técnicos

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<Technician> result = apiInstance.techniciansGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#techniciansGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Technician&gt;**](Technician.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="techniciansIdDelete"></a>
# **techniciansIdDelete**
> techniciansIdDelete(id)

Eliminar un técnico

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      apiInstance.techniciansIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#techniciansIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Técnico eliminado |  -  |

<a id="techniciansIdGet"></a>
# **techniciansIdGet**
> Technician techniciansIdGet(id)

Buscar técnico por ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Technician result = apiInstance.techniciansIdGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#techniciansIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

[**Technician**](Technician.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Técnico no encontrado |  -  |

<a id="techniciansIdPut"></a>
# **techniciansIdPut**
> techniciansIdPut(id, technician)

Modificar un técnico

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    Technician technician = new Technician(); // Technician | 
    try {
      apiInstance.techniciansIdPut(id, technician);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#techniciansIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **technician** | [**Technician**](Technician.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Técnico actualizado |  -  |

<a id="techniciansPost"></a>
# **techniciansPost**
> techniciansPost(technician)

Registrar un técnico

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Technician technician = new Technician(); // Technician | 
    try {
      apiInstance.techniciansPost(technician);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#techniciansPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **technician** | [**Technician**](Technician.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Técnico creado |  -  |
| **400** | Error de validación |  -  |

<a id="ticketsGet"></a>
# **ticketsGet**
> List&lt;Ticket&gt; ticketsGet()

Devuelve la lista de tickets

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<Ticket> result = apiInstance.ticketsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#ticketsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Ticket&gt;**](Ticket.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="ticketsIdDelete"></a>
# **ticketsIdDelete**
> ticketsIdDelete(id)

Eliminar un ticket

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      apiInstance.ticketsIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#ticketsIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Ticket eliminado |  -  |

<a id="ticketsIdGet"></a>
# **ticketsIdGet**
> Ticket ticketsIdGet(id)

Buscar ticket por ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Ticket result = apiInstance.ticketsIdGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#ticketsIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

[**Ticket**](Ticket.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Ticket no encontrado |  -  |

<a id="ticketsIdPut"></a>
# **ticketsIdPut**
> ticketsIdPut(id, ticket)

Modificar un ticket

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Long id = 56L; // Long | 
    Ticket ticket = new Ticket(); // Ticket | 
    try {
      apiInstance.ticketsIdPut(id, ticket);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#ticketsIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **ticket** | [**Ticket**](Ticket.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Ticket actualizado |  -  |

<a id="ticketsPost"></a>
# **ticketsPost**
> Ticket ticketsPost(ticket)

Registrar un ticket de avería

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Ticket ticket = new Ticket(); // Ticket | 
    try {
      Ticket result = apiInstance.ticketsPost(ticket);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#ticketsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **ticket** | [**Ticket**](Ticket.md)|  | |

### Return type

[**Ticket**](Ticket.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Ticket creado con éxito |  -  |
| **400** | Error en los datos obligatorios |  -  |

