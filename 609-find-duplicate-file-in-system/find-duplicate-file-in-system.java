import java.util.*;

class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String path : paths){
            String[] parts = path.split(" ");
            String dir = parts[0];

            for(int i = 1; i < parts.length; i++){
                String file = parts[i];

                int start = file.indexOf('(');
                int end = file.indexOf(')');

                String fileName = file.substring(0, start);
                String content = file.substring(start + 1, end);

                String fullPath = dir + "/" + fileName;

                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(fullPath);
            }
        }

        List<List<String>> result = new ArrayList<>();

        for(List<String> group : map.values()){
            if(group.size() > 1){
                result.add(group);
            }
        }

        return result;
    }
}