package today.news.http.parser;

import today.news.http.IRequest;
import today.news.http.response.IResponse;
import today.news.http.result.IResult;

public interface IParser {

    IResult parseResponse(IRequest request, IResponse iResponse);
}
