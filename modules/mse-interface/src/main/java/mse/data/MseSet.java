package mse.data;

import mse.SetManager;

import java.util.ArrayList;
import java.util.List;

public class MseSet extends MseNode<Object> {
    public List<MseCard> cards = new ArrayList<>();
    public List<MseKeyword> keywords = new ArrayList<>();
}
