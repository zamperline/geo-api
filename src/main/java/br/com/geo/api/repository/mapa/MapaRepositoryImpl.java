package br.com.geo.api.repository.mapa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.geo.api.repository.filter.MapaFilter;

public class MapaRepositoryImpl implements MapaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Object getGeoJson(MapaFilter mapaFilter) {
		try {
			if(mapaFilter.getOpcao().intValue() == 0) {
				return manager.createNativeQuery(queryZoneamento(mapaFilter)).getSingleResult();
			}else if(mapaFilter.getOpcao().intValue() == 1) {
				return manager.createNativeQuery(queryLotes(mapaFilter)).getSingleResult();
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String queryLotes(MapaFilter mapaFilter) {
		String query = "SELECT cast(row_to_json(fc) as varchar)\r\n" +
				"FROM (\r\n" + 
				"	SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features\r\n" + 
				"	FROM (\r\n" + 
				"		SELECT 'Feature' As type, ST_X(st_transform(st_centroid(geom), 4326)) as longitude, ST_Y(st_transform(st_centroid(geom), 4326)) as latitude,\r\n" + 
				"		--, json_build_object('id', imovel.id, 'situacao', imovel.situacao, 'stroke', '#ffcc60', 'stroke-width', 2, 'stroke-opacity', 1, 'fill', '#8000ff', 'fill-opacity', 0.5) As properties -- > VERSAO 9.3\r\n" + 
				"		(SELECT row_to_json((SELECT x FROM (SELECT imovel.\"QUADRA_LOT\" as \"Quadra\", imovel.\"LOTE_LOT\" as \"Lote\", imovel.\"PROPRIETARIO\" as \"Proprietário\", imovel.\"LOGRADOURO_IMOV\" as \"Logradouro\", imovel.\"BAIRRO_IMOV\" as \"Bairro\", imovel.\"NUMERO_IMOV\" as \"Número\", imovel.\"LOTE_LOT\" as \"Lote\", imovel.\"AREA_TER\" as \"Área Terreno\", imovel.id AS \"Id\", ST_X(st_transform(st_centroid(geom), 4326)) as lng, ST_Y(st_transform(st_centroid(geom), 4326)) as lat, imovel.inscricao as \"Inscrição\", imovel.situacao as \"Situação\", '#000000' AS \"stroke\", 2 as \"stroke-width\", 1 as \"stroke-opacity\", '#8000ff' as fill, 0.0 as \"fill-opacity\") x),false)) as properties,\r\n" +
				"		ST_AsGeoJSON(st_transform(((ST_DUMP(geom)).geom),4326)\\:\\:geometry('Polygon',4326))\\:\\:json As geometry\r\n" + 
				"		FROM (\r\n" + 
				"			SELECT *, st_transform(((ST_DUMP(geom)).geom),4326)\\:\\:geometry('Polygon',4326)\\:\\:geography as polygon\r\n" + 
				"			FROM lotes\r\n"
				+ "			INNER JOIN ar on ar.\"INSCRICAO\" = lotes.inscricao" + 
				"		) imovel where polygon && ST_MakeEnvelope("+ mapaFilter.getBounds().getLng1() +", "+ mapaFilter.getBounds().getLat1() +", "+ mapaFilter.getBounds().getLng2( )+", "+ mapaFilter.getBounds().getLat2() +")\r\n"
						+ "ORDER BY\r\n" + 
						"  ST_Distance(\r\n" + 
						"    Geography(polygon),\r\n" + 
						"    Geography(ST_GeographyFromText('SRID=4326;POINT("+ mapaFilter.getLngCenter()+ " " +mapaFilter.getLatCenter()+")'))\r\n" + 
						"  ) ASC" + 
				"	) As f \r\n" + 
				")  As fc;";	
		return query;
	}
	
	private String queryZoneamento(MapaFilter mapaFilter) {
		String query = "SELECT cast(row_to_json(fc) as varchar)\r\n" + 
				"FROM (\r\n" + 
				"	SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features\r\n" + 
				"	FROM (\r\n" + 
				"		SELECT 'Feature' As type, ST_X(st_transform(st_centroid(geom), 4326)) as longitude, ST_Y(st_transform(st_centroid(geom), 4326)) as latitude,\r\n" + 
				"		(SELECT row_to_json((SELECT x FROM (SELECT zoneamento.\"zoneamento\" as \"Zoneamento\", zoneamento.\"NOME\" as \"Descrição\", ST_X(st_transform(st_centroid(geom), 4326)) as lng, ST_Y(st_transform(st_centroid(geom), 4326)) as lat, '#000000' AS \"stroke\", 2 as \"stroke-width\", 1 as \"stroke-opacity\", zoneamento.\"cor\" as fill, 0.8 as \"fill-opacity\") x),false)) as properties,\r\n" + 
				"		ST_AsGeoJSON(st_transform(((ST_DUMP(geom)).geom),4326)\\:\\:geometry('Polygon',4326))\\:\\:json As geometry\r\n" + 
				"		FROM (\r\n" + 
				"			SELECT *, st_transform(((ST_DUMP(geom)).geom),4326)\\:\\:geometry('Polygon',4326)\\:\\:geography as polygon\r\n" + 
				"			FROM zoneamento\r\n" + 
				"		) zoneamento where polygon && ST_MakeEnvelope("+ mapaFilter.getBounds().getLng1() +", "+ mapaFilter.getBounds().getLat1() +", "+ mapaFilter.getBounds().getLng2( )+", "+ mapaFilter.getBounds().getLat2() +")\r\n" + 
				"	ORDER BY\r\n" + 
				"  ST_Distance(\r\n" + 
				"    Geography(polygon),\r\n" + 
				"    Geography(ST_GeographyFromText('SRID=4326;POINT("+ mapaFilter.getLngCenter()+ " " +mapaFilter.getLatCenter()+")'))\r\n" + 
				"  ) ASC	) As f \r\n" + 
				")  As fc;";	
		return query;
	}
}