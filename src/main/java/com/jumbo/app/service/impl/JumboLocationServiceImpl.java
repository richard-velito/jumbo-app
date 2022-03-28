package com.jumbo.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.app.ErrorType;
import com.jumbo.app.model.Store;
import com.jumbo.app.service.JumboLocationService;
import com.jumbo.app.util.KdTree;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JumboLocationServiceImpl implements JumboLocationService {

    public static final String jsonFile = "stores.json";

    private Map<String, Store> storeMap = new HashMap<String, Store>();
    private KdTree tree;

    public JumboLocationServiceImpl() {
        loadStores();
        buildTree();
    }

    @Override
    public List<Store> getClosetsPosition(String uuid) {
        List<Store> stores = new ArrayList<Store>();

        Store originStore = storeMap.get(uuid);
        if (originStore == null)
            throw new RuntimeException(ErrorType.StoreNotFound.toString());

        /** brute force approach **/
        //TreeMap<Double, String> distancesToStore = JumboHelper.calculateDistancesToStore(originStore, storeMap);

        /** kd-tree approach **/
        List<KdTree.Node> nearestNodes = tree.findNearest(
                new KdTree.Node(originStore.getLongitude(), originStore.getLatitude(), originStore.getUuid()), 5);
        for (KdTree.Node nearest : nearestNodes) {
            stores.add(storeMap.get(nearest.getUuid()));
        }

        return stores;
    }

    @Override
    public List<Store> getClosetsPosition(double longitude, double latitude) {
        List<Store> stores = new ArrayList<Store>();

        List<KdTree.Node> nearestNodes = tree.findNearest(new KdTree.Node(longitude, latitude, null), 5);
        for (KdTree.Node nearest : nearestNodes) {
            stores.add(storeMap.get(nearest.getUuid()));
        }

        return stores;
    }

    /* if we added an store the kd-tree needs to be recreated */
    @Override
    public void addStore(Store store) {
        storeMap.put(store.getUuid(), store);

        buildTree();
    }

    private void loadStores() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonResourceFile = new ClassPathResource(jsonFile).getFile();
            Store[] stores = mapper.readValue(jsonResourceFile, Store[].class);
            for (int i = 0; i < stores.length; i++) {
                storeMap.put(stores[i].getUuid(), stores[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(ErrorType.InternalError.toString());
        }
    }

    private void buildTree() {
        List<KdTree.Node> nodes = new ArrayList<>();
        for (String key : storeMap.keySet()) {
            nodes.add(new KdTree.Node(
                    storeMap.get(key).getLongitude(),
                    storeMap.get(key).getLatitude(),
                    storeMap.get(key).getUuid()
            ));
        }
        tree = new KdTree(nodes);
    }
}
