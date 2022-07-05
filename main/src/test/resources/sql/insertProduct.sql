INSERT INTO public.category
    (id, name, is_new)
VALUES (1, 'test', true);

INSERT INTO public.product
("id", "name", price, is_new, category_id, description, page_name)
VALUES (1, 'test_product', 10000, true, 1, 'test_description', 'test_page_name');

INSERT INTO public.product_size
    (product_id, size_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO public.storage_object
    ("path", api_path, product_id, purpose)
VALUES ('/test_dir/test_path', 'api_path', 1, 'THUMB'),
       ('/test_dir/test_path', 'api_path', 1, 'BIG_PICTURE'),
       ('/test_dir/test_path', 'api_path', 1, 'MAIN_PICTURE'),
       ('/test_dir/test_path', 'api_path', 1, 'CART_THUMB');
