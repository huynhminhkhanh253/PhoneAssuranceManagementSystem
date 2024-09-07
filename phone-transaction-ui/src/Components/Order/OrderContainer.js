import React, { useEffect } from 'react';
import './OrderContainer.css';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import { IconButton, TextField } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import SearchDialog from './SearchDialog';
import Button from '@mui/material/Button';
import SearchBar from './SearchBar';

const OrderContainer = () => {
    const navigate = useNavigate();
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);
    const [rows, setRows] = React.useState([]);
    //const [token, setToken] = React.useState(null);
    const handleInputChange = () => {
        //setToken(value);
        getData(localStorage.getItem('token'));
    }
    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };
    
    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    const handleSearch = (imei) => {
        //setToken(token);
        //setImei(imei)
        getDataByImei(imei, localStorage.getItem('token'));
    }
    const columns = [
        {   id: 'id', 
            label: 'OrderId', 
            minWidth: 50 

        },
        {   id: 'imei', 
            label: 'Imei', 
            minWidth: 80
        },
        {
            id: 'phone_model',
            label: 'Phone model',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        {
            id: 'source',
            label: 'Source',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        {
            id: 'order_status',
            label: 'Order status',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toFixed(2),
        },
        {
            id: 'ineligible_reason',
            label: 'Inneligible reason',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        {
            id: 'pending_reason',
            label: 'Pending reason',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        {
            id: 'price',
            label: 'Price',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        {
            id: 'buyer_number',
            label: 'Buyer number',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        {
            id: 'seller_number',
            label: 'Seller number',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        {
            id: 'specs_id',
            label: 'Spec Id',
            minWidth: 170,
            align: 'left',
            format: (value) => value.toLocaleString('en-US'),
        },
        ];
        const getDataByImei = async (imei, token) =>{
            try {
                const response = await fetch(`http://localhost:8081/managerService/order/getOrderByImei/${imei}`, {
                  headers: {
                    Authorization: `Bearer ${token}`,
                  },
                });
                const responseData = await response.json();
                console.log(responseData)
                setRows([responseData]);
              } catch (error) {
                //alert(imei);
                //alert(token);
                alert('Not Found');
                console.error('Fetching data failed', error);
              }     
        }
        const getData = async (token) =>{
            try {
                const response = await fetch('http://localhost:8081/managerService/order/getAllOrder', {
                  headers: {
                    Authorization: `Bearer ${localStorage.getItem('token')}`,
                  },
                });
                const responseData = await response.json();
                setRows(responseData);
              } catch (error) {
                alert('Fetching data failed\n ' + error);
                navigate('/login');
                console.error('Fetching data failed', error);
              }     
        }
        useEffect(() => {
            //getData(token)
            //setRows()
        }, [rows]);
        

    return (
        <div>
            <div style={{marginTop:"10px"}} className='ordercontainer_tile'>LIST OF ORDER</div>
            <div style={{marginTop: "20px", justifyContent:"center"}}>
                <div style={{marginLeft:"100px"}}>
                    <SearchBar onSearch={handleSearch}></SearchBar>
                </div>
                <div style={{marginTop:"-60px"}}>
                    <Button onClick={handleInputChange} variant="outlined">Get all order</Button>
                </div>
                
                {/* <SearchDialog onSubmit={handleSearch}/> */}
                
            </div>
            
            <div className='ordercontainer_container'>
                <Paper sx={{ width: '100%', overflow: 'hidden' }}>
                    <TableContainer sx={{ maxHeight: 440 }}>
                        <Table stickyHeader aria-label="sticky table">
                            <TableHead>
                                <TableRow>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{ minWidth: column.minWidth }}
                                    >
                                        {column.label}
                                    </TableCell>
                                ))}
                                </TableRow>
                            </TableHead>
                            <TableBody>
                            {rows
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row) => {
                            return (
                                <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                                {columns.map((column) => {
                                    const value = row[column.id];
                                    return (
                                    <TableCell key={column.id} align={column.align}>
                                        {column.format && typeof value === 'number'
                                        ? column.format(value)
                                        : value}
                                    </TableCell>
                                    );
                                })}
                                </TableRow>
                            );
                            })}
                            </TableBody>
                        </Table>
                    </TableContainer>
                    <TablePagination
                        rowsPerPageOptions={[5, 10, 15]}
                        component="div"
                        count={rows.length}
                        rowsPerPage={rowsPerPage}
                        page={page}
                        onPageChange={handleChangePage}
                        onRowsPerPageChange={handleChangeRowsPerPage}
                    />
                </Paper>
            </div>
        </div>
        );
    };

export default OrderContainer;