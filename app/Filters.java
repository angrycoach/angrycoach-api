import com.google.inject.Inject;
import play.http.HttpFilters;
import play.mvc.EssentialAction;
import play.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;


import java.util.ArrayList;
import java.util.List;

public class Filters extends EssentialFilter implements HttpFilters {

    @Inject
    private CORSFilter corsFilter;


    @Override
    public EssentialAction apply(EssentialAction next) {
        return corsFilter.asJava().apply(next);
    }

    @Override
    public EssentialFilter[] filters() {
        final List<EssentialFilter> filters = new ArrayList<>();
        filters.add(corsFilter.asJava());
        return filters.toArray(new EssentialFilter[0]);
    }
}