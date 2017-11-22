package br.com.geo.api.repository.mapa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.geo.api.repository.filter.MapaFilter;

public class MapaRepositoryImpl implements MapaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Object getGeoJson(MapaFilter mapaFilter) {

		String query = "SELECT cast(row_to_json(fc) as varchar)\r\n" +
				"FROM (\r\n" + 
				"	SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features\r\n" + 
				"	FROM (\r\n" + 
				"		SELECT 'Feature' As type, ST_X(st_transform(st_centroid(geom), 4326)) as longitude, ST_Y(st_transform(st_centroid(geom), 4326)) as latitude,\r\n" + 
				"		--, json_build_object('id', lt.id, 'situacao', lt.situacao, 'stroke', '#ffcc60', 'stroke-width', 2, 'stroke-opacity', 1, 'fill', '#8000ff', 'fill-opacity', 0.5) As properties -- > VERSAO 9.3\r\n" + 
				"		(SELECT row_to_json((SELECT x FROM (SELECT lt.id AS \"Id\", ST_X(st_transform(st_centroid(geom), 4326)) as lng, ST_Y(st_transform(st_centroid(geom), 4326)) as lat, lt.inscricao as \"Inscrição\", lt.situacao as \"Situação\", '#000000' AS \"stroke\", 2 as \"stroke-width\", 1 as \"stroke-opacity\", '#8000ff' as fill, 0.0 as \"fill-opacity\") x),false)) as properties,\r\n" + 
				"		ST_AsGeoJSON(st_transform(((ST_DUMP(geom)).geom),4326)\\:\\:geometry('Polygon',4326))\\:\\:json As geometry\r\n" + 
				"		FROM (\r\n" + 
				"			SELECT *, st_transform(((ST_DUMP(geom)).geom),4326)\\:\\:geometry('Polygon',4326)\\:\\:geography as polygon\r\n" + 
				"			FROM lotes\r\n"
				+ "			INNER JOIN ar on ar.\"INSCRICAO\" = lotes.inscricao" + 
				"		) lt where polygon && ST_MakeEnvelope("+ mapaFilter.getBounds().getLng1() +", "+ mapaFilter.getBounds().getLat1() +", "+ mapaFilter.getBounds().getLng2( )+", "+ mapaFilter.getBounds().getLat2() +")\r\n"
						+ "ORDER BY\r\n" + 
						"  ST_Distance(\r\n" + 
						"    Geography(polygon),\r\n" + 
						"    Geography(ST_GeographyFromText('SRID=4326;POINT("+ mapaFilter.getLngCenter()+ " " +mapaFilter.getLatCenter()+")'))\r\n" + 
						"  ) ASC" + 
				"	) As f \r\n" + 
				")  As fc;";	
		try {
			return manager.createNativeQuery(query).getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
}